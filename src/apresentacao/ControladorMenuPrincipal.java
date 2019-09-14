package apresentacao;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;

import utilidade.Alerta;
import utilidade.TIPO_TELA;

public class ControladorMenuPrincipal {

    private final Stage stage = new Stage();
    private Parent root;

    @FXML
    void menuTelaCaixa(ActionEvent event) {

	try {

	    root = FXMLLoader.load(getClass().getResource("Caixa.fxml"));
	    stage.setTitle("Menu de Caixa");
	   final var scene = new Scene(root);
	    new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
	    stage.setScene(scene);
	    stage.show();
	} catch (IOException e) {
	    Alerta.alertaErro(e.getMessage());
	}
    }

    @FXML
    void menuTelaCidade(ActionEvent event) {
	final var telaCidade = new ControladorMenuCidade();
	telaCidade.abreTelaCidadeMenu(TIPO_TELA.EXCLUI);
    }

    @FXML
    void menuTelaCliente(ActionEvent event) {

	final var telaCliente = new ControladorMenuCliente();
	telaCliente.abreTelaClienteMenu(TIPO_TELA.EXCLUI);
    }

    @FXML
    void menuTelaFuncionario(ActionEvent event) {

	final var telaFuncionario = new ControladorMenuFuncionario();
	telaFuncionario.abreTelaFuncionarioMenu();
    }

    @FXML
    void menuTelaProduto(ActionEvent event) {

	final var telaProduto = new ControladorMenuProduto();
	telaProduto.abreTelaProdutoMenu(TIPO_TELA.EXCLUI);

    }

    @FXML
    void menuTelaVenda(ActionEvent event) {

	try {
	    root = FXMLLoader.load(getClass().getResource("Venda.fxml"));

	    stage.setTitle("Menu de Venda");
	    final var scene = new Scene(root);
	    new JMetro(scene,Main.style).setAutomaticallyColorPanes(true);
	    stage.setScene(scene);
	    stage.show();

	} catch (IOException e) {
	    Alerta.alertaErro(e.getMessage());
	}
    }
}
