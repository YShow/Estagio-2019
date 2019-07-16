package apresentacao.insere;

import java.io.IOException;
import java.sql.SQLException;

import utilidade.TIPO_TELA;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import negocio.NegProduto;
import objeto.Produto;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControladorInserirProduto {

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
    private static TIPO_TELA tipo_tela;

    public void abreTelaProdutoInsere(final TIPO_TELA tipo_tela) {
	ControladorInserirProduto.tipo_tela = tipo_tela;
	Parent root;
	var stage = new Stage();

	var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/insere/ProdutoInsere.fxml"));
	    root = loader.load();
	    stage.setScene(new Scene(root, 600, 450));
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	    if (tipo_tela.equals(TIPO_TELA.ALTERA)) {
		var controlador = (ControladorInserirProduto) loader.getController();
		controlador.btnGravar.setText("Alterar");
		stage.setTitle("Alterar Produto");
		stage.show();
	    } else if (tipo_tela.equals(TIPO_TELA.INSERE)) {
		stage.setTitle("Inserir Produto");
		stage.show();
	    }
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    @FXML
    void btnGravar(ActionEvent event) {
	var produto = new NegProduto();
	try {
	    if (tipo_tela.equals(TIPO_TELA.ALTERA)) {

		var resultado = produto.alterar(pegaProduto());

	    }

	    else if (tipo_tela.equals(TIPO_TELA.INSERE)) {
		System.out.println("insere");
	    }
	} catch (SQLException e) {
	    e.getMessage();
	}
    }

    Produto pegaProduto() {
	var produto = new Produto();
	if (!(txtNome.getText().isBlank() && txtPreco.getText().isBlank() && txtQuantidade.getText().isBlank())) {

	    produto.setAtivo(true);
	    produto.setNome(txtNome.getText());
	    produto.setPreco(Double.parseDouble(txtPreco.getText()));
	    produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
	    return produto;
	} else {
	    return null;
	}
    }

}
