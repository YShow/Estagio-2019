package apresentacao;

import apresentacao.insere.ControladorInserirProduto;
import apresentacao.insere.ControladorInserirProduto.INSERE_ALTERA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControladorMenuProduto {

    private static final ControladorInserirProduto tela = new ControladorInserirProduto();
    @FXML
    private TextField txtProduto;
    @FXML
    void btnAlteraProduto(ActionEvent event) {
	tela.abreTelaProdutoInsere(INSERE_ALTERA.ALTERA);
    }

    @FXML
    void btnConsultaProduto(ActionEvent event) {

    }

    @FXML
    void btnDesativaProduto(ActionEvent event) {

    }

    @FXML
    void btnInsereProduto(ActionEvent event) {
	tela.abreTelaProdutoInsere(INSERE_ALTERA.INSERE);
    }
}
