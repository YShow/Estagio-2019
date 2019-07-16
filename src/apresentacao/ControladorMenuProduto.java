package apresentacao;

import java.io.IOException;

import apresentacao.insere.ControladorInserirProduto;
import utilidade.TIPO_TELA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    public void abreTelaProdutoMenu(final TIPO_TELA tipo_tela) {
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
		stage.show();
	    }
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    @FXML
    void btnAlteraProduto(ActionEvent event) {
	tela.abreTelaProdutoInsere(TIPO_TELA.ALTERA);
    }

    @FXML
    void btnConsultaProduto(ActionEvent event) {
	if (txtProduto.getText().isBlank()) {
	    // TODO
	    System.out.println("nao tem nada");
	}
    }

    @FXML
    void btnDesativaProduto(ActionEvent event) {

    }

    @FXML
    void btnInsereProduto(ActionEvent event) {
	tela.abreTelaProdutoInsere(TIPO_TELA.INSERE);
    }
}
