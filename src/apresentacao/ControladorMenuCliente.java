package apresentacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import utilidade.TIPO_TELA;
import apresentacao.insere.ControladorInserirCliente;

public class ControladorMenuCliente {
    @FXML
    private TextField txtCliente;
    private static final ControladorInserirCliente tela = new ControladorInserirCliente();

    @FXML
    void btnAlterarCliente(ActionEvent event) {
	tela.abreTelaClienteInsere(TIPO_TELA.ALTERA);
    }

    @FXML
    void btnConsultaCliente(ActionEvent event) {

    }

    @FXML
    void btnDesativarCliente(ActionEvent event) {

    }

    @FXML
    void btnInsereCliente(ActionEvent event) {
	tela.abreTelaClienteInsere(TIPO_TELA.INSERE);
    }

}
