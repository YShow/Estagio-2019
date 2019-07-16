package apresentacao.insere;

import java.io.IOException;

import apresentacao.ControladorMenuCliente;
import apresentacao.ControladorMenuProduto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

    public void abreTelaProdutoInsere(final TIPO_TELA tipo_tela) {
	Parent root;
	var stage = new Stage();

	var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/insere/VendaInsere.fxml"));
	    root = loader.load();
	    stage.setScene(new Scene(root, 600, 450));
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	    if (tipo_tela.equals(TIPO_TELA.ALTERA)) {
		var controlador = (ControladorInserirVenda) loader.getController();
		controlador.btnGravar.setText("Alterar");
		stage.setTitle("Alterar Venda");
		stage.show();
	    } else if (tipo_tela.equals(TIPO_TELA.INSERE)) {
		stage.setTitle("Inserir Venda");
		stage.show();
	    }
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    @FXML
    void btnPesquisaCliente(ActionEvent event) {
	var telaCliente = new ControladorMenuCliente();
	telaCliente.abreTelaClienteMenu(TIPO_TELA.CONSULTA);
    }

    @FXML
    void btnPesquisaProduto(ActionEvent event) {
	var telaProduto = new ControladorMenuProduto();
	telaProduto.abreTelaProdutoMenu(TIPO_TELA.CONSULTA);
	;
    }
}
