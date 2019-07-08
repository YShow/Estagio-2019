package apresentacao;

import apresentacao.insere.ControladorInserirProduto;
import utilidade.TIPO_TELA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControladorMenuProduto {

    private static final ControladorInserirProduto tela = new ControladorInserirProduto();
    @FXML
    private TextField txtProduto;

    @FXML
    void btnAlteraProduto(ActionEvent event) {
	tela.abreTelaProdutoInsere(TIPO_TELA.ALTERA);
    }

    @FXML
    void btnConsultaProduto(ActionEvent event) {
	if (txtProduto.getText().isBlank()) {
	    // TODO
	    System.out.println("nao tem nada");
	}
    }

    @FXML
    void btnDesativaProduto(ActionEvent event) {

    }

    @FXML
    void btnInsereProduto(ActionEvent event) {
	tela.abreTelaProdutoInsere(TIPO_TELA.INSERE);
    }
}
