package apresentacao;

import java.io.IOException;
import java.sql.SQLException;

import apresentacao.insere.ControladorInserirCidade;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegCidade;
import objeto.Cidade;
import objeto.Funcionario;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public final class ControladorMenuCidade {

	@FXML
	private TextField txtConsullaCidade;

	@FXML
	private TableView<Cidade> tvCidade;

	@FXML
	private TableColumn<Cidade, Integer> tcCodigo;

	@FXML
	private TableColumn<Cidade, String> tcNome;
	@FXML
	private Button btnDesativar;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnInserir;
	@FXML
	private TableColumn<Cidade, String> tcEstado;
	private static TIPO_TELA tipo_telaa;
	private final ControladorInserirCidade tela = new ControladorInserirCidade();
	private static Cidade cidadeVolta;
	private final NegCidade negCidade = new NegCidade();

	public Cidade abreTelaCidadeMenu(final TIPO_TELA tipo_tela) {

		try {
			tipo_telaa = tipo_tela;
			final var stage = new Stage();

			final var loader = new FXMLLoader(getClass().getResource("/apresentacao/Cidade.fxml"));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Consulta Cidade");
			final Parent root = loader.load();
			final ControladorMenuCidade controlador = loader.getController();
			controlador.txtConsullaCidade.setOnKeyPressed(e -> {
				if (e.getCode().equals(KeyCode.ENTER)) {
					controlador.btnConsultaCidade(null);
				}
			});

			final var scene = new Scene(root);
			new JMetro(scene, Main.style);
			stage.setScene(scene);

			if (tipo_tela.equals(TIPO_TELA.CONSULTA)) {

				controlador.btnAlterar.setDisable(true);
				controlador.btnDesativar.setText("Selecionar");
				controlador.btnInserir.setDisable(true);
				stage.setTitle("Consultar Cidade");
				stage.showAndWait();
				return cidadeVolta;
			} else {
				if (!Funcionario.getFuncionario().getAdministrador()) {
					controlador.btnDesativar.setDisable(true);
				}
				stage.show();
			}

		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
		return null;
	}

	@FXML
	private void btnAlteraCidade(final ActionEvent event) {
		final var cidade = tvCidade.getSelectionModel().getSelectedItem();
		tela.abreTelaCidadeInsere(TIPO_TELA.ALTERA, cidade);
	}

	@FXML
	private void btnConsultaCidade(final ActionEvent event) {

		try {
			if (!txtConsullaCidade.getText().isBlank()) {
				limpaTabela();

				final var funcionario = negCidade.consultar(txtConsullaCidade.getText().trim());
				if (!funcionario.isEmpty()) {
					tvCidade.setItems(FXCollections.observableList(funcionario));
					tcCodigo.setCellValueFactory(new PropertyValueFactory<Cidade, Integer>("Codigo"));
					tcNome.setCellValueFactory(new PropertyValueFactory<Cidade, String>("Nome"));
					tcEstado.setCellValueFactory(new PropertyValueFactory<Cidade, String>("Estado"));
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
		tvCidade.getItems().clear();
		tvCidade.refresh();
	}

	@FXML
	private void btnDesativaCidade(final ActionEvent event) {
		final var cidade = tvCidade.getSelectionModel().getSelectedItem();

		if (tipo_telaa.equals(TIPO_TELA.CONSULTA)) {
			cidadeVolta = cidade;
			btnDesativar.getScene().getWindow().hide();

		} else {

			try {

				if (negCidade.excluir(cidade.getCodigo())) {
					tvCidade.getItems().remove(cidade);
					Alerta.alertaSucesso();
				}
			} catch (final SQLException e) {
				Alerta.alertaCidadeEmUso();
			}
		}
	}

	@FXML
	private void btnInsereCidade(final ActionEvent event) {
		tela.abreTelaCidadeInsere(TIPO_TELA.INSERE, null);
	}

}
