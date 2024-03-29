package apresentacao;

import java.io.IOException;
import java.sql.SQLException;

import apresentacao.insere.ControladorInserirProduto;
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
import negocio.NegProduto;
import objeto.Funcionario;
import objeto.Produto;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public final class ControladorMenuProduto {

	private final ControladorInserirProduto tela = new ControladorInserirProduto();
	@FXML
	private TextField txtProduto;

	@FXML
	private Button btnDesativaProduto;

	@FXML
	private Button btnAlteraProduto;

	@FXML
	private Button btnInsereProduto;
	@FXML
	private TableView<Produto> tvProduto;

	@FXML
	private TableColumn<Produto, Integer> tcCodigo;

	@FXML
	private TableColumn<Produto, String> tcNome;

	@FXML
	private TableColumn<Produto, Double> tcPreco;

	@FXML
	private TableColumn<Produto, Integer> tcQuantidade;

	@FXML
	private TableColumn<Produto, Boolean> tcAtivo;
	private static TIPO_TELA tipo_telaa;
	private static Produto produtoAlterar;
	private final NegProduto negProduto = new NegProduto();

	public void abreTelaProdutoMenu(final TIPO_TELA tipo_tela) {

		try {
			tipo_telaa = tipo_tela;
			final var stage = new Stage();
			final var loader = new FXMLLoader(getClass().getResource("/apresentacao/Produto.fxml"));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Consulta Produto");
			final Parent root = loader.load();
			final var scene = new Scene(root);
			new JMetro(scene, Main.style);
			stage.setScene(scene);
			final ControladorMenuProduto controlador = loader.getController();
			controlador.txtProduto.setOnKeyPressed(e -> {
				if (e.getCode().equals(KeyCode.ENTER)) {
					controlador.btnConsultaProduto(null);
				}
			});
			if (!Funcionario.funcionario.isAdministrador()) {
				controlador.btnDesativaProduto.setDisable(true);
			}
			stage.show();
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	public Produto abreTelaProdutoMenuAlterar(final TIPO_TELA tipo_tela) {

		try {
			tipo_telaa = tipo_tela;
			final var stage = new Stage();
			final var loader = new FXMLLoader();
			stage.initModality(Modality.APPLICATION_MODAL);
			loader.setLocation(getClass().getResource("/apresentacao/Produto.fxml"));
			final Parent root = loader.load();

			final var scene = new Scene(root);
			new JMetro(scene, Main.style);
			stage.setScene(scene);
			final ControladorMenuProduto controlador = loader.getController();
			if (tipo_tela.equals(TIPO_TELA.CONSULTA)) {

				controlador.btnInsereProduto.setDisable(true);
				controlador.btnInsereProduto.setVisible(false);
				controlador.btnAlteraProduto.setDisable(true);
				controlador.btnAlteraProduto.setVisible(false);

				controlador.btnDesativaProduto.setText("Selecionar");
				stage.setTitle("Consultar produto ");
				stage.showAndWait();
			}
			if (!Funcionario.funcionario.isAdministrador()) {
				controlador.btnDesativaProduto.setDisable(true);
			}

		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
		return produtoAlterar;
	}

	@FXML
	private void btnAlteraProduto(final ActionEvent event) {
		final var produto = tvProduto.getSelectionModel().getSelectedItem();
		tela.abreTelaProdutoInsere(TIPO_TELA.ALTERA, produto);
	}

	@FXML
	private void btnConsultaProduto(final ActionEvent event) {

		try {
			if (!txtProduto.getText().isBlank()) {
				limpaTabela();

				final var funcionario = negProduto.consultar(txtProduto.getText().trim());
				if (!funcionario.isEmpty()) {
					tvProduto.setItems(FXCollections.observableList(funcionario));
					tcCodigo.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("Codigo"));
					tcNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("Nome"));
					tcPreco.setCellValueFactory(new PropertyValueFactory<Produto, Double>("Preco"));
					tcQuantidade.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("Quantidade"));
					tcAtivo.setCellValueFactory(new PropertyValueFactory<Produto, Boolean>("Ativo"));
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
		tvProduto.getItems().clear();
		tvProduto.refresh();
	}

	@FXML
	private void btnDesativaProduto(final ActionEvent event) {
		final var produto = tvProduto.getSelectionModel().getSelectedItem();
		if (tipo_telaa.equals(TIPO_TELA.CONSULTA)) {

			produtoAlterar = produto;
			btnDesativaProduto.getScene().getWindow().hide();
		} else {

			try {
				if (negProduto.excluir(produto.getCodigo())) {

					tvProduto.getItems().remove(produto);
					Alerta.alertaSucesso();

				}
			} catch (final SQLException e) {
				Alerta.alertaErro(e.getMessage());
			}
		}
	}

	@FXML
	private void btnInsereProduto(final ActionEvent event) {
		tela.abreTelaProdutoInsere(TIPO_TELA.INSERE, null);
	}
}
