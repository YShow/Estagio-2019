package apresentacao.insere;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import apresentacao.ControladorMenuCliente;
import apresentacao.ControladorMenuProduto;
import apresentacao.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegVendas;
import objeto.Cliente;
import objeto.Produto;
import objeto.Vendas;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public class ControladorInserirVenda {
    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtProduto;

    @FXML
    private TextField txtQtdEstoque;

    @FXML
    private TextField txtQtd;

    @FXML
    private TextField txtPrecoUnitario;

    @FXML
    private TextField txtPrecoTotal;

    @FXML
    private Button btnGravar;
   

    public void abreTelaVendaInsere(final TIPO_TELA tipo_tela, Vendas venda) {

	Parent root;
	final var stage = new Stage();

	final var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/insere/VendaInsere.fxml"));
	    root = loader.load();
	    final var scene = new Scene(root);
	    new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
	    stage.setScene(scene);
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	   final var controlador = (ControladorInserirVenda) loader.getController();
	    if (tipo_tela.equals(TIPO_TELA.ALTERA)) {
		
		controlador.txtCliente.setText(String.valueOf(venda.getCliente().getCodigo()));
		stage.setTitle("Alterar Venda");
		stage.show();
	    } else if (tipo_tela.equals(TIPO_TELA.INSERE)) {
	    	formataCampo(controlador);
		stage.setTitle("Inserir Venda");
		stage.show();
	    }
	} catch (IOException e) {
	    Alerta.alertaErro(e.getMessage());
	}
    }

    @FXML
    void btnPesquisaCliente(ActionEvent event) {
	final var telaCliente = new ControladorMenuCliente();
	final var cliente = telaCliente.abreTelaClienteMenuAlterar(TIPO_TELA.CONSULTA);
	atualizaValorCliente(cliente);
    }

    @FXML
    void btnPesquisaProduto(ActionEvent event) {
	final var telaProduto = new ControladorMenuProduto();
	final var produto = telaProduto.abreTelaProdutoMenuAlterar(TIPO_TELA.CONSULTA);
	atualizaValorProduto(produto);
    }

    private void atualizaValorCliente(Cliente cliente) {

	txtCliente.setText(String.valueOf(cliente.getCodigo()));
    }

    private void atualizaValorProduto(Produto produto) {
	txtProduto.setText(String.valueOf(produto.getCodigo()));
	txtQtdEstoque.setText(String.valueOf(produto.getQuantidade()));
	txtPrecoUnitario.setText(String.valueOf(produto.getPreco()));
    }
    private void formataCampo(ControladorInserirVenda controlador) {
    	final var errado = "#F24B4B";
    	controlador.txtQtd.setTextFormatter(new TextFormatter<String>(change -> {
    	if(!change.getText().isBlank())
    	{
    		   final var too = new Tooltip();	
    	if(Integer.valueOf(change.getControlNewText()) > Integer.valueOf(controlador.txtQtdEstoque.getText()))
    	  {
    		too.setText("Quantidade maior do que em estoque");
    		too.setShowDuration(Duration.seconds(3));
    		change.getControl().setTooltip(too);    		
    		change.getControl().getTooltip().show(change.getControl().getScene().getWindow());
    		change.getControl().setStyle("-fx-control-inner-background: " + errado);
    		  return null;
    	  }
    	  else
    	  {
    		if(!change.getControlNewText().isBlank())
    		{
    			final var qtd = Integer.valueOf(change.getControlNewText());
    			final var preco = Double.valueOf(controlador.txtPrecoUnitario.getText());
    			final var total = qtd * preco;
    			final var novoFormato = new DecimalFormat("#.##");
    			controlador.txtPrecoTotal.setText(novoFormato.format(total));
    		}
    		  return change;
    	  }
    	  }
    		return change;
    	    
    	}));
    }
  
    @FXML
    void btnGravar(ActionEvent event) {
    	final var negVenda = new NegVendas();
    	//negVenda.inserir();
    }
}
