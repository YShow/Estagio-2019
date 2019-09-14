package apresentacao.insere;

import java.io.IOException;

import apresentacao.ControladorMenuCliente;
import apresentacao.ControladorMenuProduto;
import apresentacao.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;

import objeto.Cliente;
import objeto.Produto;
import objeto.Vendas;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public class ControladorInserirVenda {
    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtProduto;

    @FXML
    private TextField txtQtdEstoque;

    @FXML
    private TextField txtQtd;

    @FXML
    private TextField txtPrecoUnitario;

    @FXML
    private TextField txtPrecoTotal;

    @FXML
    private Button btnGravar;

    public void abreTelaVendaInsere(final TIPO_TELA tipo_tela, Vendas venda) {

	Parent root;
	final var stage = new Stage();

	final var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/insere/VendaInsere.fxml"));
	    root = loader.load();
	   final var scene = new Scene(root);
	    new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
	    stage.setScene(scene);
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	    if (tipo_tela.equals(TIPO_TELA.ALTERA)) {
		final var controlador = (ControladorInserirVenda) loader.getController();
		controlador.txtCliente.setText(String.valueOf(venda.getCliente().getCodigo()));
		stage.setTitle("Alterar Venda");
		stage.show();
	    } else if (tipo_tela.equals(TIPO_TELA.INSERE)) {
		stage.setTitle("Inserir Venda");
		stage.show();
	    }
	} catch (IOException e) {
	   Alerta.alertaErro(e.getMessage());
	}
    }

    @FXML
    void btnPesquisaCliente(ActionEvent event) {
	final var telaCliente = new ControladorMenuCliente();
	telaCliente.abreTelaClienteMenu(TIPO_TELA.CONSULTA);
	atualizaValorCliente();
    }

    @FXML
    void btnPesquisaProduto(ActionEvent event) {
	final var telaProduto = new ControladorMenuProduto();
	telaProduto.abreTelaProdutoMenu(TIPO_TELA.CONSULTA);
	atualizaValorProduto();
    }

    private void atualizaValorCliente() {

	txtCliente.setText(String.valueOf(Cliente.CodClienteGet()));
    }

    private void atualizaValorProduto() {
	txtProduto.setText(String.valueOf(Produto.CodProdutoGet()));
    }
}
