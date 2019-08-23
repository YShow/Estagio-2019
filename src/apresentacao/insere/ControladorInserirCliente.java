package apresentacao.insere;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objeto.Cliente;
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

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtTelefone;

    @FXML
    private CheckBox chkAtivo;
    private static TIPO_TELA tipo_telaa;
    public void abreTelaClienteInsere(final TIPO_TELA tipo_tela,Cliente cliente) {
	tipo_telaa = tipo_tela;
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
	    var controlador = (ControladorInserirCliente) loader.getController();
	    if (tipo_tela.equals(tipo_telaa.ALTERA)) {		
		controlador.btnGrava.setText("Alterar");
		controlador.chkAtivo.setSelected((cliente.getAtivo()));
		controlador.txtCidade.setText(cliente.getCidade().getNome());
		controlador.txtCodigo.setText(String.valueOf(cliente.getCodigo()));
		controlador.txtCPF.setText(cliente.getCPF());
		controlador.txtEndereco.setText(cliente.getEndereco());
		controlador.txtNome.setText(cliente.getNome());
		controlador.txtTelefone.setText(cliente.getTelefone());
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
