package apresentacao;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControladorMenuPrincipal {

    private static final Stage stage = new Stage();
    private static Parent root;

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

	try {

	    root = FXMLLoader.load(getClass().getResource("Cidade.fxml"));

	    stage.setTitle("Menu de Cidade");
	    stage.setScene(new Scene(root, 600, 450));
	    stage.show();

	} catch (IOException e) {
	    e.getMessage();
	}
    }

    @FXML
    void menuTelaCliente(ActionEvent event) {

	try {
	    root = FXMLLoader.load(getClass().getResource("Cliente.fxml"));

	    stage.setTitle("Menu de Cliente");
	    stage.setScene(new Scene(root, 600, 450));
	    stage.show();

	} catch (IOException e) {
	    e.getMessage();
	}
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

	try {
	    root = FXMLLoader.load(getClass().getResource("Produto.fxml"));

	    stage.setTitle("Menu de Produto");
	    stage.setScene(new Scene(root, 600, 450));
	    stage.show();

	} catch (IOException e) {
	    e.getMessage();
	}
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
