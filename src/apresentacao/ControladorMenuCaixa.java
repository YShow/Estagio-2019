package apresentacao;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

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

	public void abreTelaCaixaMenu() {
		try {
			final var stage = new Stage();

			final var loader = new FXMLLoader();
			stage.initModality(Modality.APPLICATION_MODAL);
			loader.setLocation(getClass().getResource("/apresentacao/Caixa.fxml"));
			final Parent root = loader.load();


			final var scene = new Scene(root);
			new JMetro(scene, Main.style);
			stage.setScene(scene);
			final ControladorMenuCaixa controlador = loader.getController();
			controlador.dpData.setOnKeyPressed(e -> {
				if(e.getCode().equals(KeyCode.ENTER)) {
					controlador.conulstaCaixa(null);
				}
			});
			if (!Funcionario.getFuncionario().getAdministrador()) {

				controlador.btnDesativarCaixa.setDisable(true);
			}
			stage.show();
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	void btnAlterarCaixa(final ActionEvent event) {

	}

	@FXML
	void btnDesativarCaixa(final ActionEvent event) {

	}

	@FXML
	void btnInserirCaixa(final ActionEvent event) {

	}

	@FXML
	void conulstaCaixa(final ActionEvent event) {
		final var negCaixa = new NegCaixa();
		try {
			final var caixas = negCaixa.consultar(dpData.getValue());

			tcAtivo.setCellValueFactory(new PropertyValueFactory<Caixa, Boolean>("ativo"));
			tcCodCli.setCellValueFactory(new PropertyValueFactory<Caixa, Integer>("cliente"));
			tcCodigo.setCellValueFactory(new PropertyValueFactory<Caixa, Integer>("codigo"));
			tcData.setCellValueFactory(new PropertyValueFactory<Caixa, LocalDate>("data"));
			tcPreco.setCellValueFactory(new PropertyValueFactory<Caixa, Double>("precototal"));
			tcSaida.setCellValueFactory(new PropertyValueFactory<Caixa, Integer>("saida"));


			tvCaixa.setItems(FXCollections.observableList(caixas));

		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}

	}
}
