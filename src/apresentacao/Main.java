package apresentacao;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utilidade.Alerta;

public class Main extends Application {

    private static final double HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
	// isto é um teste para como esconder uma tela e abrir outra sem fexar o
	// programa
	// Platform.setImplicitExit(false);
	
	try {
	    var login = new ControladorTelaLogin();
	    login.start(primaryStage);	    

	}  catch (Exception e) {
	    Alerta.alertaErro(e.getMessage());
	}
	
	
    }

    public static void main(String[] args) {
	launch(args);
    }
}
