package apresentacao.insere;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;

import apresentacao.ControladorMenuCliente;
import apresentacao.ControladorMenuProduto;
import apresentacao.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegVendas;
import objeto.Caixa;
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
    @FXML
    private TextField txtFormaPagamento;

    @FXML
    private TextField txtSaida;
    @FXML
    private CheckBox chkAtivo;
    private static TIPO_TELA tipo_telaa;
   

    public void abreTelaVendaInsere(final TIPO_TELA tipo_tela, Vendas venda) {
    	tipo_telaa = tipo_tela;
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
		preencheCampos(controlador,venda.getCodigo());
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
    		
    		  return null;
    	  }
    	  else
    	  {
    		if(!change.getControlNewText().isBlank())
    		{
    			final var qtd = Integer.valueOf(change.getControlNewText());
    			final var preco = Double.valueOf(controlador.txtPrecoUnitario.getText());
    			final var total = qtd * preco;
    			var valor = new BigDecimal(total).setScale(2,RoundingMode.HALF_EVEN);
    			controlador.txtPrecoTotal.setText(valor.toString());
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
    	final var venda = new Vendas();
    	final var cliente = new Cliente();
    	final var produto = new Produto();
    	final var caixa = new Caixa();
    	
    	//PEGA CODIGO DO CLIENTE
    	cliente.setCodigo(Integer.valueOf(txtCliente.getText()));
    	
    	//PEGA PRODUTO
    	produto.setCodigo(Integer.valueOf(txtProduto.getText()));
    	produto.setPreco(Double.valueOf(txtPrecoUnitario.getText()));
    	produto.setQuantidade(Integer.valueOf(txtQtd.getText()));
    	
    	//PEGA CAIXA
    	caixa.setAtivo(true);
    	caixa.setCliente(Integer.valueOf(txtCliente.getText()));
    	caixa.setData(LocalDate.now());
    	
    	final var precototal = new BigDecimal(Double.valueOf(txtPrecoTotal.getText()));
    	
    	caixa.setPrecototal(precototal.doubleValue());
    	caixa.setSaida(Double.valueOf(txtSaida.getText()));
    	
    	
    	venda.setAtivo(true);
    	venda.setData(LocalDate.now());
    	    	
    	venda.setCliente(cliente);
    	venda.setFormaPagamento(txtFormaPagamento.getText());
    	
    	venda.setProduto(produto);
    	venda.setCaixa(caixa);
    	
    	try {
    		if(tipo_telaa.equals(TIPO_TELA.INSERE))
			negVenda.inserir(venda);
    		else
    			negVenda.alterar(venda);
		} catch (SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}
    }

    private void preencheCampos(ControladorInserirVenda controlador,int idVenda)
    {
    	final var negVenda = new NegVendas();
    	try {
			final var venda = negVenda.pegaVendaAlterar(idVenda);
			
			controlador.txtFormaPagamento.setText(venda.getFormaPagamento());
			controlador.txtPrecoTotal.setText(String.valueOf(venda.getCaixa().getPrecototal()));
			controlador.txtPrecoUnitario.setText(String.valueOf(venda.getProduto().getPreco()));
			controlador.txtProduto.setText(String.valueOf(venda.getProduto().getCodigo()));
			controlador.txtQtd.setText(String.valueOf(venda.getProduto().getQuantidade()));
			controlador.chkAtivo.setSelected(venda.isAtivo());
		} catch (SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}
    }
}
