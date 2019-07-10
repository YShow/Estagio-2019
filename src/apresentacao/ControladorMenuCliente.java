package apresentacao;

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

import java.io.IOException;

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
	tela.abreTelaClienteInsere(TIPO_TELA.ALTERA);
    }

    @FXML
    void btnConsultaCliente(ActionEvent event) {

    }

    @FXML
    void btnDesativarCliente(ActionEvent event) {

    }

    @FXML
    void btnInsereCliente(ActionEvent event) {
	tela.abreTelaClienteInsere(TIPO_TELA.INSERE);
    }

}
