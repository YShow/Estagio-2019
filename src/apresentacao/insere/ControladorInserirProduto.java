package apresentacao.insere;

import java.io.IOException;
import java.sql.SQLException;

import apresentacao.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegProduto;
import objeto.Produto;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public final class ControladorInserirProduto {

	@FXML
	private TextField txtCodigo;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtQuantidade;

	@FXML
	private TextField txtPreco;
	@FXML
	private Button btnGravar;
	@FXML
	private CheckBox chkAtivo;
	@FXML
	private Label lblCodigo;
	private static TIPO_TELA tipo_telaa;
	private final NegProduto negProduto = new NegProduto();

	public void abreTelaProdutoInsere(final TIPO_TELA tipo_tela, final Produto produto) {

		try {
			tipo_telaa = tipo_tela;

			final var stage = new Stage();
			final var loader = new FXMLLoader();
			stage.initModality(Modality.APPLICATION_MODAL);
			loader.setLocation(getClass().getResource("/apresentacao/insere/ProdutoInsere.fxml"));
			final Parent root = loader.load();
			final var scene = new Scene(root);
			new JMetro(scene, Main.style);
			stage.setScene(scene);
			final ControladorInserirProduto controlador = loader.getController();
			if (tipo_tela.equals(TIPO_TELA.ALTERA)) {

				controlador.btnGravar.setText("Alterar");
				controlador.txtCodigo.setText(String.valueOf(produto.getCodigo()));
				controlador.txtNome.setText(produto.getNome());
				controlador.txtPreco.setText(String.valueOf(produto.getPreco()));
				controlador.txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
				controlador.chkAtivo.setSelected(produto.getAtivo());
				stage.setTitle("Alterar Produto");
				stage.show();
			} else if (tipo_tela.equals(TIPO_TELA.INSERE)) {
				controlador.txtCodigo.setVisible(false);
				controlador.lblCodigo.setVisible(false);
				stage.setTitle("Inserir Produto");
				stage.show();
			}
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnGravar(final ActionEvent event) {
		try {

			if (tipo_telaa == TIPO_TELA.INSERE) {
				if (verificaValores() && negProduto.inserir(pegaProduto())) {
					Alerta.alertaSucesso();
					btnGravar.getScene().getWindow().hide();
				}
			} else {
				if (verificaValores() && negProduto.alterar(pegaProduto())) {
					Alerta.alertaSucesso();
					btnGravar.getScene().getWindow().hide();
				}
			}
		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}

	}

	private Produto pegaProduto() {
		final var produto = new Produto();
		produto.setAtivo(chkAtivo.isSelected());
		if (!txtCodigo.getText().isBlank()) {
			produto.setCodigo(Integer.parseInt(txtCodigo.getText().trim()));
		}
		produto.setNome(txtNome.getText().trim());
		produto.setPreco(Double.parseDouble(txtPreco.getText().trim()));
		produto.setQuantidade(Integer.parseInt(txtQuantidade.getText().trim()));
		return produto;
	}

	private boolean verificaValores() {
		final var erros = new StringBuilder();

		if (txtNome.getText().isBlank()) {
			erros.append("Nome esta vazio. \n");
		}
		if (txtPreco.getText().isBlank()) {
			erros.append("Preço esta vazio. \n");
		}
		if (txtQuantidade.getText().isBlank()) {
			erros.append("Quantidade esta vazio. \n");
		}
		if (erros.length() != 0) {
			Alerta.alertaCampoNulo(erros.toString());
		}

		return erros.length() == 0;

	}
}
