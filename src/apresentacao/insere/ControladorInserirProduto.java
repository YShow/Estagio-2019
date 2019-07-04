package apresentacao.insere;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorInserirProduto {
    private Stage stage;
    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtPreco;
    @FXML
    private Button btnGravar;

    public enum INSERE_ALTERA {
	INSERE, ALTERA
    }
    
     public void abreTelaProdutoInsere(final INSERE_ALTERA insere_altera) {	
	Parent root;
	stage = new Stage();
	stage.initModality(Modality.APPLICATION_MODAL);
	if (insere_altera.equals(INSERE_ALTERA.ALTERA)) {
	    try {
		
		var loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/apresentacao/insere/ProdutoInsere.fxml"));
		root = loader.load();		
		var controlador = (ControladorInserirProduto) loader.getController();
		controlador.btnGravar.setText("Alterar");
				
		stage.setTitle("Alterar Produto");
		stage.setScene(new Scene(root, 600, 450));
		stage.setMinHeight(root.minHeight(-1));
		stage.setMinWidth(root.minWidth(-1));
		stage.show();
		
		
	    } catch (IOException e) {
		System.out.println(e.getMessage());
	    }
	} else {
	    try {
		
		var loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/apresentacao/insere/ProdutoInsere.fxml"));
		root = loader.load();
		
		stage.setTitle("Inserir Produto");
		stage.setScene(new Scene(root, 600, 450));
		stage.setMinHeight(root.minHeight(-1));
		stage.setMinWidth(root.minWidth(-1));
		stage.show();
		
	    } catch (IOException e) {
		System.out.println(e.getMessage());
	    }
	}
    }

    @FXML
    void btnGravar(ActionEvent event) {

    }

}
