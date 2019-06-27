package apresentacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
	// isto é um teste para como esconder uma tela e abrir outra sem fexar o
	// programa
	// Platform.setImplicitExit(false);
	try {
	    final var root = (BorderPane) FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
	    final var scene = new Scene(root, HEIGHT, HEIGHT);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    primaryStage.setTitle("Canta Galo");
	    primaryStage.setMinHeight(HEIGHT);
	    primaryStage.setMinWidth(HEIGHT);
	    primaryStage.setScene(scene);
	    primaryStage.show();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	launch(args);
    }
}
