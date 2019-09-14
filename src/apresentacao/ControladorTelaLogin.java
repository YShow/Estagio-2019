package apresentacao;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegLogin;
import objeto.Funcionario;
import utilidade.Alerta;

public class ControladorTelaLogin extends Application {
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;
    private static Stage stage;
    
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
   private  void btnLogin(ActionEvent event) {
	if (txtUsuario.getText().isBlank() && txtSenha.getText().isBlank())
	    Alerta.alertaCampoNulo();
	else {
	    final var funcionario = new Funcionario();
	    funcionario.setUsuario(txtUsuario.getText());
	    funcionario.setSenha(txtSenha.getText());
	    var login = new NegLogin();
	    try {
		if (login.verificaLogin(funcionario)) {
		    // fexa tela de login login
		    stage.close();
		    // abre tela do menu apos login
		    abreTelaMenuPrincipal();
		} else
		    Alerta.alertaCampoNulo();
	    } catch (SQLException e) {
		Alerta.alertaErro(e.getMessage());

	    }
	}

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	
	
	stage = primaryStage;	
	stage.initStyle(StageStyle.TRANSPARENT);
	abreTelaLogin();
    }

    private void abreTelaLogin() {

	try {
	    final var root = (AnchorPane) FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
	   
	    final var scene = new Scene(root);
	    new JMetro(scene,Main.style).setAutomaticallyColorPanes(true);
	    stage.setTitle("Canta Galo");
	    stage.setScene(scene);
	    stage.show();

	} catch (IOException e) {
	    Alerta.alertaErro(e.getMessage());
	}

    }

    private void abreTelaMenuPrincipal() {
	try {
	    final var root = (BorderPane) FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
	    final var scene = new Scene(root);
	    new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
	    
	    
	    root.setOnMousePressed(event -> {
	            xOffset = event.getSceneX();
	            yOffset = event.getSceneY();
	        });

	        
	        //move around here
	    root.setOnMouseDragged(event -> {
	            stage.setX(event.getScreenX() - xOffset);
	            stage.setY(event.getScreenY() - yOffset);
	});
	    
	    
	    stage.setTitle("Canta Galo");	    
	    stage.setScene(scene);
	    stage.show();
	} catch (IOException e) {
	    Alerta.alertaErro(e.getMessage());
	}
    }
}
