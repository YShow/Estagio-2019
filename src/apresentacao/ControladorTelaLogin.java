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
import negocio.NegLogin;
import objeto.Funcionario;
import utilidade.Alerta;

public class ControladorTelaLogin extends Application {
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;
    private static Stage stage;

    @FXML
    void btnLogin(ActionEvent event) {
	if (txtUsuario.getText().isBlank() && txtSenha.getText().isBlank())
	    Alerta.alertaCampoNulo();
	else {
	    final var funcionario = new Funcionario();
	    funcionario.setNome(txtUsuario.getText());
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
	abreTelaLogin();
    }

    public void abreTelaLogin() {

	try {
	    final var root = (AnchorPane) FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
	    final var scene = new Scene(root);
	    stage.setTitle("Canta Galo");
	    stage.setScene(scene);
	    stage.show();

	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}

    }

    public void abreTelaMenuPrincipal() {
	try {
	    final var root = (BorderPane) FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
	    final var scene = new Scene(root, 500, 500);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    stage.setTitle("Canta Galo");
	    stage.setMinHeight(600);
	    stage.setMinWidth(600);
	    stage.setScene(scene);
	    stage.show();
	} catch (IOException e) {
	    Alerta.alertaErro(e.getMessage());
	}
    }
}
