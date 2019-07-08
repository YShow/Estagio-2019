package apresentacao;

import apresentacao.insere.ControladorInserirCidade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import utilidade.TIPO_TELA;

public class ControladorMenuCidade {

    @FXML
    private TextField txtConsullaCidade;
    private static final ControladorInserirCidade tela = new ControladorInserirCidade();

    @FXML
    void btnAlteraCidade(ActionEvent event) {
	tela.abreTelaCidadeInsere(TIPO_TELA.ALTERA);
    }

    @FXML
    void btnConsultaCidade(ActionEvent event) {

    }

    @FXML
    void btnDesativaCidade(ActionEvent event) {

    }

    @FXML
    void btnInsereCidade(ActionEvent event) {
	tela.abreTelaCidadeInsere(TIPO_TELA.INSERE);
    }

}
