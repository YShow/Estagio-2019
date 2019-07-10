package apresentacao;

import apresentacao.insere.ControladorInserirVenda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import utilidade.TIPO_TELA;

public class ControladorMenuVenda {
    private final ControladorInserirVenda tela = new ControladorInserirVenda();
    @FXML
    private TextField txtVenda;
    
    @FXML
    void btnAlteraVenda(ActionEvent event) {
	tela.abreTelaProdutoInsere(TIPO_TELA.ALTERA);
    }

    @FXML
    void btnConsultaVenda(ActionEvent event) {

    }

    @FXML
    void btnDesativaVenda(ActionEvent event) {

    }

    @FXML
    void btnInsereVenda(ActionEvent event) {
	tela.abreTelaProdutoInsere(TIPO_TELA.INSERE);
    }

}
