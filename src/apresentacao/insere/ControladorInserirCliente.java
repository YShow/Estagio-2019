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
import utilidade.TIPO_TELA;

public class ControladorInserirCliente {
    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCidade;

    @FXML
    private Button btnGrava;

    public void abreTelaClienteInsere(final TIPO_TELA tipo_tela) {
	var stage = new Stage();
	Parent root;
	var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/insere/ClienteInsere.fxml"));
	    root = loader.load();
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	    stage.setScene(new Scene(root, 600, 450));

	    if (tipo_tela.equals(TIPO_TELA.ALTERA)) {
		var controlador = (ControladorInserirCliente) loader.getController();
		controlador.btnGrava.setText("Alterar");
		stage.setTitle("Alterar Cliente");
		stage.show();
	    } else if (tipo_tela.equals(TIPO_TELA.INSERE)) {
		stage.setTitle("Inserir Cliente");
		stage.show();
	    }
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    @FXML
    void btnGrava(ActionEvent event) {

    }

    @FXML
    void btnPesquisarCidade(ActionEvent event) {

    }
}
