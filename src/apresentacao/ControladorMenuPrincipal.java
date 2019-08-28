package apresentacao;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilidade.TIPO_TELA;

public class ControladorMenuPrincipal {

    private final Stage stage = new Stage();
    private Parent root;

    @FXML
    void menuTelaCaixa(ActionEvent event) {

	try {

	    root = FXMLLoader.load(getClass().getResource("Caixa.fxml"));
	    stage.setTitle("Menu de Caixa");
	    stage.setScene(new Scene(root, 600, 450));
	    stage.show();
	} catch (IOException e) {
	    e.getMessage();
	}
    }

    @FXML
    void menuTelaCidade(ActionEvent event) {
	var telaCidade = new ControladorMenuCidade();
	telaCidade.abreTelaCidadeMenu(TIPO_TELA.EXCLUI);
    }

    @FXML
    void menuTelaCliente(ActionEvent event) {

	var telaCliente = new ControladorMenuCliente();
	telaCliente.abreTelaClienteMenu(TIPO_TELA.EXCLUI);
    }

    @FXML
    void menuTelaFuncionario(ActionEvent event) {

	try {
	    root = FXMLLoader.load(getClass().getResource("Funcionario.fxml"));

	    stage.setTitle("Menu de Funcionario");
	    stage.setScene(new Scene(root, 600, 450));
	    stage.show();

	} catch (IOException e) {
	    e.getMessage();
	}
    }

    @FXML
    void menuTelaProduto(ActionEvent event) {

	var telaProduto = new ControladorMenuProduto();
	telaProduto.abreTelaProdutoMenu(TIPO_TELA.EXCLUI);

    }

    @FXML
    void menuTelaVenda(ActionEvent event) {

	try {
	    root = FXMLLoader.load(getClass().getResource("Venda.fxml"));

	    stage.setTitle("Menu de Venda");
	    stage.setScene(new Scene(root, 600, 450));
	    stage.show();

	} catch (IOException e) {
	    e.getMessage();
	}
    }
}
