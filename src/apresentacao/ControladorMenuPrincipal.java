package apresentacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utilidade.ControladorBotaoConfirma;

public class ControladorMenuPrincipal {

    @FXML
    private static void fexarPrograma(ActionEvent event) {
	var confirma = new ControladorBotaoConfirma();

	 char sim_nao = confirma.btnChama();
	if (sim_nao == 'y') {
	    System.exit(0);
	}
    }
}
