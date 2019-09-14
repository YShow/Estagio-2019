package apresentacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import apresentacao.insere.ControladorInserirCliente;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import jfxtras.styles.jmetro.JMetro;

import negocio.NegCliente;
import objeto.Cliente;
import objeto.Funcionario;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

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
    private TableColumn<Cliente, String> tcCidade;
    @FXML
    private TableColumn<Cliente, Integer> tcCodCidade;
    private final ControladorInserirCliente tela = new ControladorInserirCliente();
    private static TIPO_TELA tipo_telaa;

    public void abreTelaClienteMenu(final TIPO_TELA tipo_tela) {
	tipo_telaa = tipo_tela;
	final var stage = new Stage();
	Parent root;
	final var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/Cliente.fxml"));
	    root = loader.load();
	    final var controlador = (ControladorMenuCliente) loader.getController();
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	    final var scene = new Scene(root);
	    new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
	    stage.setScene(scene);
	    if (tipo_tela.equals(TIPO_TELA.CONSULTA)) {

		controlador.btnInsereCliente.setDisable(true);
		controlador.btnInsereCliente.setVisible(false);
		controlador.btnAlterarCliente.setDisable(true);
		controlador.btnAlterarCliente.setVisible(false);
		controlador.btnDesativarCliente.setText("Selecionar");
		stage.setTitle("Consultar Cliente");
		stage.showAndWait();
	    }
	    if (!Funcionario.getFuncionario().getAdministrador()) {
		btnDesativarCliente.setVisible(true);
		btnDesativarCliente.setDisable(true);
	    }
	    stage.show();
	} catch (IOException e) {
	    Alerta.alertaErro(e.getMessage());
	}
    }

    @FXML
    void btnAlterarCliente(ActionEvent event) {
	final var cliente = tvCliente.getSelectionModel().getSelectedItem();
	tela.abreTelaClienteInsere(TIPO_TELA.ALTERA, cliente);
    }

    @FXML
    void btnConsultaCliente(ActionEvent event) {
	final var negCliente = new NegCliente();
	try {
	    final List<Cliente> cliente = negCliente.consultar(txtCliente.getText());
	    final var data = FXCollections.observableList(cliente);
	    tvCliente.setItems(data);
	    tcCodigo.setCellValueFactory(new PropertyValueFactory("Codigo"));
	    tcAtivo.setCellValueFactory(new PropertyValueFactory("Ativo"));
	    tcCPF.setCellValueFactory(new PropertyValueFactory("CPF"));
	    tcEndereco.setCellValueFactory(new PropertyValueFactory("Endereco"));
	    tcNome.setCellValueFactory(new PropertyValueFactory("Nome"));
	    tcTelefone.setCellValueFactory(new PropertyValueFactory("Telefone"));
	    // workaround para selecionar apenas o nome da cidade em vez de a referencia de
	    // memoria
	    tcCidade.setCellValueFactory(
		    new Callback<TableColumn.CellDataFeatures<Cliente, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Cliente, String> param) {
			    return new ReadOnlyStringWrapper(param.getValue().getCidade().getNome());
			}
		    });

	    tcCodCidade.setCellValueFactory(
		    new Callback<TableColumn.CellDataFeatures<Cliente, Integer>, ObservableValue<Integer>>() {

			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Cliente, Integer> param) {
			    return new ReadOnlyObjectWrapper<>(param.getValue().getCidade().getCodigo());
			}
		    });

	} catch (SQLException e) {

	    Alerta.alertaErro(e.getMessage());
	}
    }

    @FXML
    void btnDesativarCliente(ActionEvent event) {
	final var cliente = tvCliente.getSelectionModel().getSelectedItem();
	if (tipo_telaa.equals(TIPO_TELA.CONSULTA)) {

	    Cliente.codCliente = cliente.getCodigo();
	} else {
	    final var negCliente = new NegCliente();
	    try {
		if (negCliente.excluir(cliente.getCodigo())) {
		    tvCliente.getItems().remove(cliente);
		    Alerta.alertaSucesso();
		}
	    } catch (SQLException e) {
		Alerta.alertaClienteEmCaixa();
	    }
	}
    }

    @FXML
    void btnInsereCliente(ActionEvent event) {
	tela.abreTelaClienteInsere(TIPO_TELA.INSERE, null);
    }

}
