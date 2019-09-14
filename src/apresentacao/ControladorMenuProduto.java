package apresentacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import apresentacao.insere.ControladorInserirProduto;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;

import negocio.NegProduto;
import objeto.Funcionario;
import objeto.Produto;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public class ControladorMenuProduto {

    private final ControladorInserirProduto tela = new ControladorInserirProduto();
    @FXML
    private TextField txtProduto;

    @FXML
    private Button btnDesativaProduto;

    @FXML
    private Button btnAlteraProduto;

    @FXML
    private Button btnInsereProduto;
    @FXML
    private TableView<Produto> tvProduto;

    @FXML
    private TableColumn<Produto, Integer> tcCodigo;

    @FXML
    private TableColumn<Produto, String> tcNome;

    @FXML
    private TableColumn<Produto, Double> tcPreco;

    @FXML
    private TableColumn<Produto, Integer> tcQuantidade;

    @FXML
    private TableColumn<Produto, Boolean> tcAtivo;
    private static TIPO_TELA tipo_telaa;

    public void abreTelaProdutoMenu(final TIPO_TELA tipo_tela) {
	tipo_telaa = tipo_tela;
	final var stage = new Stage();
	Parent root;
	var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/Produto.fxml"));
	    root = loader.load();
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	    final var scene = new Scene(root);
	    new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
	    stage.setScene(scene);
	    var controlador = (ControladorMenuProduto) loader.getController();
	    if (tipo_tela.equals(TIPO_TELA.CONSULTA)) {
		
		controlador.btnInsereProduto.setDisable(true);
		controlador.btnInsereProduto.setVisible(false);
		controlador.btnAlteraProduto.setDisable(true);
		controlador.btnAlteraProduto.setVisible(false);

		controlador.btnDesativaProduto.setText("Selecionar");
		stage.setTitle("Consultar produto ");
		stage.showAndWait();
	    }if(!Funcionario.getFuncionario().getAdministrador())
		{controlador.btnDesativaProduto.setVisible(false);
	    	controlador.btnDesativaProduto.setDisable(true);
		}
	    stage.show();
	} catch (IOException e) {
	    Alerta.alertaErro(e.getMessage());
	}
    }

    @FXML
    void btnAlteraProduto(ActionEvent event) {
	final var produto = tvProduto.getSelectionModel().getSelectedItem();
	tela.abreTelaProdutoInsere(TIPO_TELA.ALTERA, produto);
    }

    @FXML
    void btnConsultaProduto(ActionEvent event) {
	final var negProduto = new NegProduto();
	try {
	    final List<Produto> funcionario = negProduto.consultar(txtProduto.getText());
	    final var data = FXCollections.observableList(funcionario);
	    tvProduto.setItems(data);
	    tcCodigo.setCellValueFactory(new PropertyValueFactory("Codigo"));
	    tcNome.setCellValueFactory(new PropertyValueFactory("Nome"));
	    tcPreco.setCellValueFactory(new PropertyValueFactory("Preco"));
	    tcQuantidade.setCellValueFactory(new PropertyValueFactory("Quantidade"));
	    tcAtivo.setCellValueFactory(new PropertyValueFactory("Ativo"));

	} catch (SQLException e) {

	    Alerta.alertaErro(e.getMessage());
	}
    }

    @FXML
    void btnDesativaProduto(ActionEvent event) {
	final var produto = tvProduto.getSelectionModel().getSelectedItem();
	if (tipo_telaa.equals(TIPO_TELA.CONSULTA)) {
	    
	    Produto.codProduto = produto.getCodigo();
	} else {
	    final var negProduto = new NegProduto();
	    try {
		if (negProduto.excluir(produto.getCodigo())) {

		    tvProduto.getItems().remove(produto);
		    Alerta.alertaSucesso();

		}
	    } catch (SQLException e) {
		Alerta.alertaErro(e.getMessage());
	    }
	}
    }

    @FXML
    void btnInsereProduto(ActionEvent event) {
	tela.abreTelaProdutoInsere(TIPO_TELA.INSERE, null);
    }
}
