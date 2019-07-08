package apresentacao;

import apresentacao.insere.ControladorInserirFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import utilidade.TIPO_TELA;

public class ControladorMenuFuncionario {

    @FXML
    private TextField txtFuncionario;
    private static final ControladorInserirFuncionario tela = new ControladorInserirFuncionario();

    @FXML
    void btnAlteraFuncionario(ActionEvent event) {
	tela.abreTelaFuncionarioInsere(TIPO_TELA.ALTERA);
    }

    @FXML
    void btnConsultaFuncionario(ActionEvent event) {

    }

    @FXML
    void btnDesativaFuncionario(ActionEvent event) {

    }

    @FXML
    void btnInsereFuncionario(ActionEvent event) {
	tela.abreTelaFuncionarioInsere(TIPO_TELA.INSERE);
    }
}
