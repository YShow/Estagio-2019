package apresentacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import apresentacao.insere.ControladorInserirProduto;
import utilidade.TIPO_TELA;
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
import negocio.NegCidade;
import negocio.NegProduto;
import objeto.Cidade;
import objeto.Cliente;
import objeto.Produto;

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
	var stage = new Stage();
	Parent root;
	var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/Produto.fxml"));
	    root = loader.load();
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	    stage.setScene(new Scene(root, 600, 450));

	    if (tipo_tela.equals(TIPO_TELA.CONSULTA)) {
		var controlador = (ControladorMenuProduto) loader.getController();
		controlador.btnInsereProduto.setDisable(true);
		controlador.btnInsereProduto.setVisible(false);
		controlador.btnAlteraProduto.setDisable(true);
		controlador.btnAlteraProduto.setVisible(false);

		controlador.btnDesativaProduto.setText("Selecionar");
		stage.setTitle("Consultar produto ");
		stage.showAndWait();
	    }
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    @FXML
    void btnAlteraProduto(ActionEvent event) {
	final var produto = tvProduto.getSelectionModel().getSelectedItem();
	tela.abreTelaProdutoInsere(TIPO_TELA.ALTERA,produto);
    }

    @FXML
    void btnConsultaProduto(ActionEvent event) {
	final var negProduto = new NegProduto();
	try {
	    List<Produto> funcionario = negProduto.consultar(txtProduto.getText());
	    var data = FXCollections.observableList(funcionario);
	    tvProduto.setItems(data);
	    tcCodigo.setCellValueFactory(new PropertyValueFactory("Codigo"));
	    tcNome.setCellValueFactory(new PropertyValueFactory("Nome"));
	    tcPreco.setCellValueFactory(new PropertyValueFactory("Preco"));
	    tcQuantidade.setCellValueFactory(new PropertyValueFactory("Quantidade"));
	    tcAtivo.setCellValueFactory(new PropertyValueFactory("Ativo"));
	    
	} catch (SQLException e) {
	    
	    System.out.println(e.getMessage());
	}
    }

    @FXML
    void btnDesativaProduto(ActionEvent event) {
	if(tipo_telaa.equals(TIPO_TELA.CONSULTA))
	{
	    var produto = tvProduto.getSelectionModel().getSelectedItem();
	    System.out.println(produto.getCodigo());
	    Produto.codProduto = produto.getCodigo();
	}
	else
	{
	    
	}
    }

    @FXML
    void btnInsereProduto(ActionEvent event) {
	tela.abreTelaProdutoInsere(TIPO_TELA.INSERE,null);
    }
}
