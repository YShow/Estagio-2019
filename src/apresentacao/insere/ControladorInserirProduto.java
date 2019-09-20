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
	private static TIPO_TELA tipo_telaa;

	public void abreTelaProdutoInsere(final TIPO_TELA tipo_tela, final Produto produto) {
		tipo_telaa = tipo_tela;
		Parent root;
		final var stage = new Stage();

		final var loader = new FXMLLoader();
		stage.initModality(Modality.APPLICATION_MODAL);

		try {
			loader.setLocation(getClass().getResource("/apresentacao/insere/ProdutoInsere.fxml"));
			root = loader.load();
			final var scene = new Scene(root);
			new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
			stage.setScene(scene);
			stage.setMinHeight(root.minHeight(-1));
			stage.setMinWidth(root.minWidth(-1));
			if (tipo_tela.equals(TIPO_TELA.ALTERA)) {
				final var controlador = (ControladorInserirProduto) loader.getController();
				controlador.btnGravar.setText("Alterar");
				controlador.txtCodigo.setText(String.valueOf(produto.getCodigo()));
				controlador.txtNome.setText(produto.getNome());
				controlador.txtPreco.setText(String.valueOf(produto.getPreco()));
				controlador.txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
				controlador.chkAtivo.setSelected(produto.getAtivo());
				stage.setTitle("Alterar Produto");
				stage.show();
			} else if (tipo_tela.equals(TIPO_TELA.INSERE)) {
				stage.setTitle("Inserir Produto");
				stage.show();
			}
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnGravar(final ActionEvent event) {
		final var negProduto = new NegProduto();
		final var produto = new Produto();
		if (tipo_telaa == TIPO_TELA.INSERE) {
			produto.setAtivo(chkAtivo.isSelected());
			produto.setNome(txtNome.getText());
			produto.setPreco(Double.valueOf(txtPreco.getText()));
			produto.setQuantidade(Integer.valueOf(txtQuantidade.getText()));
			try {
				negProduto.inserir(produto);
			} catch (final SQLException e) {
				Alerta.alertaErro(e.getMessage());
			}
		} else {
			produto.setAtivo(chkAtivo.isSelected());
			produto.setCodigo(Integer.valueOf(txtCodigo.getText()));
			produto.setNome(txtNome.getText());
			produto.setPreco(Double.valueOf(txtPreco.getText()));
			produto.setQuantidade(Integer.valueOf(txtQuantidade.getText()));

			try {
				negProduto.alterar(produto);
			} catch (final SQLException e) {
				Alerta.alertaErro(e.getMessage());
			}
		}

	}

}
