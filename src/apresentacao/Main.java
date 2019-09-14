package apresentacao;


import javafx.application.Application;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.Style;
import utilidade.Alerta;

public class Main extends Application {


    public final static Style style = Style.LIGHT ;
    @Override
    public void start(Stage primaryStage) {
	// isto é um teste para como esconder uma tela e abrir outra sem fexar o
	// programa
	//TODO
	// Platform.setImplicitExit(false);

	try {
	    var login = new ControladorTelaLogin();
	    login.start(primaryStage);

	} catch (Exception e) {
	    Alerta.alertaErro(e.getMessage());
	}

    }

    public static void main(String[] args) {
	launch(args);
    }
}
