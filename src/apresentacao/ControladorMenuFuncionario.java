package apresentacao;

import java.sql.SQLException;
import java.util.List;

import apresentacao.insere.ControladorInserirFuncionario;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.NegFuncionario;
import objeto.Funcionario;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public class ControladorMenuFuncionario {

    @FXML
    private TextField txtFuncionario;
    @FXML
    private TableView<Funcionario> tblFuncionario;

    @FXML
    private TableColumn<Funcionario, Integer> tcCodigo;

    @FXML
    private TableColumn<Funcionario, String> tcNome;

    @FXML
    private TableColumn<Funcionario, String> tcFuncao;

    @FXML
    private TableColumn<Funcionario, String> tcSenha;

    @FXML
    private TableColumn<Funcionario, String> tcAdm;
    private final ControladorInserirFuncionario tela = new ControladorInserirFuncionario();

    @FXML
    void btnAlteraFuncionario(ActionEvent event) {
	final var funcionario = tblFuncionario.getSelectionModel().getSelectedItem();
	System.out.println(funcionario.getNome());
	tela.abreTelaFuncionarioInsere(TIPO_TELA.ALTERA, funcionario);
    }

    @FXML
    void btnConsultaFuncionario(ActionEvent event) {
	final var negFuncionario = new NegFuncionario();
	try {
	    List<Funcionario> funcionario = negFuncionario.consultar(txtFuncionario.getText());
	    var data = FXCollections.observableList(funcionario);
	    tblFuncionario.setItems(data);
	    tcCodigo.setCellValueFactory(new PropertyValueFactory("Codigo"));
	    tcNome.setCellValueFactory(new PropertyValueFactory("Nome"));
	    tcFuncao.setCellValueFactory(new PropertyValueFactory("Funcao"));
	    tcSenha.setCellValueFactory(new PropertyValueFactory("Senha"));
	    tcAdm.setCellValueFactory(new PropertyValueFactory("Administrador"));
	} catch (SQLException e) {

	    System.out.println(e.getMessage());
	}
    }

    @FXML
    void btnDesativaFuncionario(ActionEvent event) {
	final var negFuncionario = new NegFuncionario();
	final var funcionario = tblFuncionario.getSelectionModel().getSelectedItem();
	try {
	    if (negFuncionario.excluir(funcionario.getCodigo())) {
		tblFuncionario.getItems().remove(funcionario);
		Alerta.alertaSucesso();
	    }
	} catch (SQLException e) {
	    Alerta.alertaErro(e.getMessage());
	}
    }

    @FXML
    void btnInsereFuncionario(ActionEvent event) {
	tela.abreTelaFuncionarioInsere(TIPO_TELA.INSERE, null);
    }
}
