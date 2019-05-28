package apresentacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double HEIGHT = 600;

    public void start(Stage primaryStage) {
	try {
	    var root = (BorderPane) FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
	    var scene = new Scene(root, HEIGHT, HEIGHT);
	    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
