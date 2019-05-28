package utilidade;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class ControladorBotaoConfirma {
    private static final char SIM = 'y';
    private static final char NAO = 'n';
    private static final int TAMANHO = 400;

    public char btnChama() {

	try {
	    var alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Deseja sair do programa?");
	    alert.setContentText("");
	    var yesButton = new ButtonType("Sim", ButtonData.YES);
	    var noButton = new ButtonType("Não", ButtonData.NO);
	    alert.getButtonTypes().setAll(yesButton, noButton);
	    alert.setHeight(TAMANHO);
	    alert.setWidth(TAMANHO);
	    alert.setResizable(true);
	    var botaclicado = alert.showAndWait();
	    if (botaclicado.isPresent() && botaclicado.get().equals(yesButton)) {
		return SIM;
	    } else if (botaclicado.isPresent() && botaclicado.get().equals(noButton)) {
		return NAO;
	    } else {
		alert.close();
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return 0;

    }

}
