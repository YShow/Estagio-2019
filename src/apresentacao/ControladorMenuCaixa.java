package apresentacao;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import apresentacao.insere.ControladorInserirCaixa;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegCaixa;
import objeto.Caixa;
import objeto.Funcionario;
import utilidade.Alerta;

public final class ControladorMenuCaixa {

	@FXML
	private DatePicker dpData;

	@FXML
	private TableView<Caixa> tvCaixa;
	@FXML
	private Button btnDesativarCaixa;

	@FXML
	private TableColumn<Caixa, Integer> tcCodigo;

	@FXML
	private TableColumn<Caixa, LocalDate> tcData;

	@FXML
	private TableColumn<Caixa, Double> tcPreco;

	@FXML
	private TableColumn<Caixa, Integer> tcSaida;

	@FXML
	private TableColumn<Caixa, Integer> tcCodCli;

	@FXML
	private TableColumn<Caixa, Boolean> tcAtivo;
	@FXML
	private TextField txtTotalValor;
	private final NegCaixa negCaixa = new NegCaixa();

	private final ControladorInserirCaixa caixaInserir = new ControladorInserirCaixa();

	public void abreTelaCaixaMenu() {
		try {
			final var stage = new Stage();

			final var loader = new FXMLLoader(getClass().getResource("/apresentacao/Caixa.fxml"));
			stage.initModality(Modality.APPLICATION_MODAL);

			final Parent root = loader.load();
			stage.setTitle("Consulta Caixa");
			final var scene = new Scene(root);
			new JMetro(scene, Main.style);
			stage.setScene(scene);
			final ControladorMenuCaixa controlador = loader.getController();
			controlador.dpData.setValue(LocalDate.now());
			controlador.dpData.setOnKeyPressed(e -> {
				if (e.getCode().equals(KeyCode.ENTER)) {
					controlador.conulstaCaixa(null);
				}
			});
			if (!Funcionario.funcionario.isAdministrador()) {

				controlador.btnDesativarCaixa.setDisable(true);
			}
			stage.show();
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	void btnInserirCaixa(final ActionEvent event) {
		caixaInserir.abreTelaCaixaMenu();
	}

	@FXML
	void conulstaCaixa(final ActionEvent event) {
		try {
			if (dpData.getValue() != null) {
				limpaTabela();
				final var caixas = negCaixa.consultar(dpData.getValue());
				if (!caixas.isEmpty()) {
					tvCaixa.setItems(FXCollections.observableList(caixas));
					tcAtivo.setCellValueFactory(new PropertyValueFactory<Caixa, Boolean>("ativo"));
					tcCodCli.setCellValueFactory(new PropertyValueFactory<Caixa, Integer>("funcionario"));
					tcCodigo.setCellValueFactory(new PropertyValueFactory<Caixa, Integer>("codigo"));
					tcData.setCellValueFactory(new PropertyValueFactory<Caixa, LocalDate>("data"));
					tcPreco.setCellValueFactory(new PropertyValueFactory<Caixa, Double>("precototal"));
					tcSaida.setCellValueFactory(new PropertyValueFactory<Caixa, Integer>("saida"));

					txtTotalValor.setText(
							String.valueOf(tvCaixa.getItems().stream().mapToDouble(Caixa::getPrecototal).sum()));
				} else {
					Alerta.alertaNaoEncontrado();
				}
			} else {
				Alerta.alertaCampoNulo();
			}

		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}

	}

	private void limpaTabela() {
		tvCaixa.getItems().clear();
		tvCaixa.refresh();
	}
}
