package apresentacao;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import apresentacao.insere.ControladorInserirVenda;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import negocio.NegVendas;
import objeto.Funcionario;
import objeto.Vendas;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public final class ControladorMenuVenda {
	private final ControladorInserirVenda tela = new ControladorInserirVenda();
	@FXML
	private TableView<Vendas> tvVenda;
	@FXML
	private DatePicker dateData;
	@FXML
	private TableColumn<Vendas, Integer> tcCodigo;

	@FXML
	private TableColumn<Vendas, Integer> tcCodCliente;

	@FXML
	private TableColumn<Vendas, Integer> tcCodCaixa;

	@FXML
	private TableColumn<Vendas, String> tcDataVenda;

	@FXML
	private TableColumn<Vendas, String> tcFormaPAg;
	@FXML
	private Button btnDesativaVenda;
	@FXML
	private TableColumn<Vendas, Boolean> tcAtivo;
	private final NegVendas negVenda = new NegVendas();

	public void abreTelaVendaMenu() {

		try {
			final var stage = new Stage();

			final var loader = new FXMLLoader(getClass().getResource("/apresentacao/Venda.fxml"));

			stage.initModality(Modality.APPLICATION_MODAL);

			final Parent root = loader.load();
			final ControladorMenuVenda control = loader.getController();

			control.dateData.setOnKeyPressed(e -> {
				if (e.getCode().equals(KeyCode.ENTER)) {
					control.btnConsultaVenda(null);
				}
			});
			control.dateData.setValue(LocalDate.now());
			final var scene = new Scene(root);
			new JMetro(scene, Main.style);
			stage.setScene(scene);
			if (!Funcionario.getFuncionario().getAdministrador()) {
				control.btnDesativaVenda.setDisable(true);
			}

			stage.show();
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnAlteraVenda(final ActionEvent event) {
		final var venda = tvVenda.getSelectionModel().getSelectedItem();
		tela.abreTelaVendaInsere(TIPO_TELA.ALTERA, venda);
	}

	@FXML
	private void btnConsultaVenda(final ActionEvent event) {

		try {
			if (dateData.getValue() != null) {
				limpaTabela();

				final var venda = negVenda.consultar(dateData.getValue());
				if (!venda.isEmpty()) {
					tvVenda.setItems(FXCollections.observableList(venda));
					tcCodigo.setCellValueFactory(new PropertyValueFactory<Vendas, Integer>("Codigo"));
					tcFormaPAg.setCellValueFactory(new PropertyValueFactory<Vendas, String>("FormaPagamento"));

					tcDataVenda.setCellValueFactory(dataVenda -> new SimpleStringProperty(
							dataVenda.getValue().getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));

					tcCodCaixa.setCellValueFactory(
							codCaixa -> new SimpleIntegerProperty(codCaixa.getValue().getCaixa().getCodigo())
									.asObject());

					tcCodCliente.setCellValueFactory(
							codCliente -> new SimpleIntegerProperty(codCliente.getValue().getCliente().getCodigo())
									.asObject());

					tcAtivo.setCellValueFactory(new PropertyValueFactory<Vendas, Boolean>("Ativo"));
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
		tvVenda.getItems().clear();
	}

	@FXML
	private void btnDesativaVenda(final ActionEvent event) {
		final var venda = tvVenda.getSelectionModel().getSelectedItem();

		try {
			if (negVenda.excluir(venda.getCodigo())) {
				tvVenda.getItems().remove(venda);
				Alerta.alertaSucesso();
			}

		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnInsereVenda(final ActionEvent event) {
		tela.abreTelaVendaInsere(TIPO_TELA.INSERE, null);
	}

}
