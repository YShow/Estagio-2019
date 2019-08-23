package apresentacao;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import negocio.NegCliente;
import negocio.NegFuncionario;
import objeto.Cidade;
import objeto.Cliente;
import objeto.Funcionario;
import utilidade.TIPO_TELA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import apresentacao.insere.ControladorInserirCliente;

public class ControladorMenuCliente {
    @FXML
    private TextField txtCliente;

    @FXML
    private Button btnDesativarCliente;

    @FXML
    private Button btnAlterarCliente;

    @FXML
    private Button btnInsereCliente;

    @FXML
    private TableView<Cliente> tvCliente;

    @FXML
    private TableColumn<Cliente, Integer> tcCodigo;

    @FXML
    private TableColumn<Cliente, String> tcNome;

    @FXML
    private TableColumn<Cliente, String> tcCPF;

    @FXML
    private TableColumn<Cliente, String> tcEndereco;

    @FXML
    private TableColumn<Cliente, String> tcTelefone;

    @FXML
    private TableColumn<Cliente, Boolean> tcAtivo;
    @FXML
    private TableColumn<Cliente,String> tcCidade;
    @FXML
    private TableColumn<Cliente, Integer> tcCodCidade;
    private final ControladorInserirCliente tela = new ControladorInserirCliente();

    public void abreTelaClienteMenu(final TIPO_TELA tipo_tela) {
	var stage = new Stage();
	Parent root;
	var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/Cliente.fxml"));
	    root = loader.load();
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	    stage.setScene(new Scene(root, 600, 450));

	    if (tipo_tela.equals(TIPO_TELA.CONSULTA)) {
		var controlador = (ControladorMenuCliente) loader.getController();
		controlador.btnInsereCliente.setDisable(true);
		controlador.btnInsereCliente.setVisible(false);
		controlador.btnAlterarCliente.setDisable(true);
		controlador.btnAlterarCliente.setVisible(false);
		controlador.btnDesativarCliente.setText("Selecionar");
		stage.setTitle("Consultar Cliente");
		stage.show();
	    }
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    @FXML
    void btnAlterarCliente(ActionEvent event) {
	var cliente =  tvCliente.getSelectionModel().getSelectedItem();
	tela.abreTelaClienteInsere(TIPO_TELA.ALTERA,cliente);
    }

    @FXML
    void btnConsultaCliente(ActionEvent event) {
	final var negCliente = new NegCliente();
	try {
	    List<Cliente> cliente = negCliente.consultar(txtCliente.getText());
	    var data = FXCollections.observableList(cliente);
	    tvCliente.setItems(data);
	    tcCodigo.setCellValueFactory(new PropertyValueFactory("Codigo"));
	    tcAtivo.setCellValueFactory(new PropertyValueFactory("Ativo"));
	    tcCPF.setCellValueFactory(new PropertyValueFactory("CPF"));
	    tcEndereco.setCellValueFactory(new PropertyValueFactory("Endereco"));
	    tcNome.setCellValueFactory(new PropertyValueFactory("Nome"));
	    tcTelefone.setCellValueFactory(new PropertyValueFactory("Telefone"));
	    //workaround para selecionar apenas o nome da cidade em vez de a referencia de memoria
	    tcCidade.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cliente,String>, ObservableValue<String>>() {
	        
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {	    	
	    	return new ReadOnlyStringWrapper(param.getValue().getCidade().getNome());
	        }
	    });
	    
	    tcCodCidade.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cliente,Integer>, ObservableValue<Integer>>() {
	        
	        @Override
	        public  ObservableValue<Integer> call(CellDataFeatures<Cliente, Integer> param) {	    	
	    	return new ReadOnlyObjectWrapper<>(param.getValue().getCidade().getCodigo());
	        }
	    });
	   
	} catch (SQLException e) {
	    
	    System.out.println(e.getMessage());
	}
    }

    @FXML
    void btnDesativarCliente(ActionEvent event) {

    }

    @FXML
    void btnInsereCliente(ActionEvent event) {
	tela.abreTelaClienteInsere(TIPO_TELA.INSERE,null);
    }

}
