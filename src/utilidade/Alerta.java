package utilidade;

import apresentacao.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import jfxtras.styles.jmetro.JMetro;

public final class Alerta {

	public static void alertaSucesso() {
		final Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		new JMetro(alerta.getDialogPane().getScene(), Main.style).setAutomaticallyColorPanes(true);

		alerta.initModality(Modality.APPLICATION_MODAL);
		alerta.setAlertType(AlertType.INFORMATION);
		alerta.setHeaderText("Função realizada com sucesso");

		alerta.showAndWait();

	}

	public static void alertaNaoEncontrado() {
		final Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		new JMetro(alerta.getDialogPane().getScene(), Main.style).setAutomaticallyColorPanes(true);

		alerta.initModality(Modality.APPLICATION_MODAL);
		alerta.setAlertType(AlertType.INFORMATION);
		alerta.setHeaderText("A pesquisa não achou resultados");
		alerta.showAndWait();

	}

	public static void alertaCustom() {
		final Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		new JMetro(alerta.getDialogPane().getScene(), Main.style).setAutomaticallyColorPanes(true);

		alerta.initModality(Modality.APPLICATION_MODAL);
		alerta.setAlertType(AlertType.INFORMATION);
		alerta.setHeaderText("Primeiro login no sistema");
		alerta.setContentText("Por favor crie um usuario administrador");

		alerta.showAndWait();

	}

	public static void alertaErro(final String erro) {
		final Alert alerta = new Alert(Alert.AlertType.ERROR);
		new JMetro(alerta.getDialogPane().getScene(), Main.style).setAutomaticallyColorPanes(true);

		alerta.initModality(Modality.APPLICATION_MODAL);
		alerta.setAlertType(AlertType.WARNING);
		alerta.setTitle("Ocorreu um problema");
		alerta.setHeaderText("Verifique os campos e informe o erro ao desenvolvedor");
		alerta.setContentText("Erro: " + erro);
		alerta.setResult(ButtonType.OK);

		alerta.showAndWait();

	}

	public static void alertaCampoNulo() {
		final Alert alerta = new Alert(Alert.AlertType.WARNING);
		new JMetro(alerta.getDialogPane().getScene(), Main.style);

		alerta.initModality(Modality.APPLICATION_MODAL);
		alerta.setHeaderText("Verifique os campos");
		alerta.setContentText("Por favor verifique se todos os campos foram preenchidos");
		alerta.setResult(ButtonType.OK);

		alerta.showAndWait();
	}

	public static void alertaCidadeEmUso() {
		final Alert alerta = new Alert(Alert.AlertType.WARNING);
		new JMetro(alerta.getDialogPane().getScene(), Main.style);

		alerta.initModality(Modality.APPLICATION_MODAL);
		alerta.setAlertType(AlertType.WARNING);
		alerta.setHeaderText("Cidade associada em cliente");
		alerta.setContentText("Não é possivel desativar esta cidade pois um cliente esta associado a ela");
		alerta.setResult(ButtonType.OK);

		alerta.showAndWait();
	}

	public static void alertaClienteEmCaixa() {
		final Alert alerta = new Alert(Alert.AlertType.WARNING);
		new JMetro(alerta.getDialogPane().getScene(), Main.style).setAutomaticallyColorPanes(true);

		alerta.initModality(Modality.APPLICATION_MODAL);
		alerta.setAlertType(AlertType.WARNING);
		alerta.setHeaderText("Cliente possui caixa");
		alerta.setContentText("Não é possivel desativar este cliente ele ja possui um caixa");
		alerta.setResult(ButtonType.OK);

		alerta.showAndWait();
	}
}
