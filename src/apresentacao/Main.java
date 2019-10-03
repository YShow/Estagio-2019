package apresentacao;

import javafx.application.Application;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.Style;
import utilidade.Alerta;

public class Main extends Application {

	public static final Style style = Style.LIGHT;

	@Override
	public void start(final Stage primaryStage) {

		try {
			final var login = new ControladorTelaLogin();
			login.start(primaryStage);

		} catch (final Exception e) {
			Alerta.alertaErro(e.getMessage());
		}

	}

	public static void main(final String[] args) {
		launch(args);
	}
}
