package apresentacao;

import java.sql.SQLException;
import java.util.List;

import apresentacao.insere.ControladorInserirCidade;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.NegCidade;
import negocio.NegFuncionario;
import objeto.Cidade;
import objeto.Funcionario;
import utilidade.TIPO_TELA;

public class ControladorMenuCidade {

    @FXML
    private TextField txtConsullaCidade;

    @FXML
    private TableView<Cidade> tvCidade;
    
    
    @FXML
    private TableColumn<Cidade, Integer> tcCodigo;

    @FXML
    private TableColumn<Cidade, String> tcNome;

    @FXML
    private TableColumn<Cidade, String> tcEstado;
    private final ControladorInserirCidade tela = new ControladorInserirCidade();

    @FXML
    void btnAlteraCidade(ActionEvent event) {
	final var cidade = tvCidade.getSelectionModel().getSelectedItem();
	tela.abreTelaCidadeInsere(TIPO_TELA.ALTERA,cidade);
    }

    @FXML
    void btnConsultaCidade(ActionEvent event) {
	final var negCidade = new NegCidade();
	try {
	    List<Cidade> funcionario = negCidade.consultar(txtConsullaCidade.getText());
	    var data = FXCollections.observableList(funcionario);
	    tvCidade.setItems(data);
	    tcCodigo.setCellValueFactory(new PropertyValueFactory("Codigo"));
	    tcNome.setCellValueFactory(new PropertyValueFactory("Nome"));
	    tcEstado.setCellValueFactory(new PropertyValueFactory("Estado"));
	    
	} catch (SQLException e) {
	    
	    System.out.println(e.getMessage());
	}
    }

    @FXML
    void btnDesativaCidade(ActionEvent event) {

    }

    @FXML
    void btnInsereCidade(ActionEvent event) {
	tela.abreTelaCidadeInsere(TIPO_TELA.INSERE,null);
    }

}
