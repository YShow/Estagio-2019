package apresentacao.insere;

import java.io.IOException;
import java.sql.SQLException;

import apresentacao.ControladorMenuCidade;
import apresentacao.Main;
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
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import negocio.NegCliente;
import objeto.Cidade;
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
    @FXML
    private TextField txtCodCidade;
    private static TIPO_TELA tipo_telaa;

    public void abreTelaClienteInsere(final TIPO_TELA tipo_tela, Cliente cliente) {
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
	    var scene = new Scene(root);
	    new JMetro(scene,Main.style).setAutomaticallyColorPanes(true);
	    stage.setScene(scene);
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
		controlador.txtCodCidade.setText(String.valueOf(cliente.getCidade().getCodigo()));
		controlador.btnGrava.setText("Alterar");
		stage.setTitle("Alterar Cliente");
		stage.showAndWait();
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
	final var negCliente = new NegCliente();
	final var cliente = new Cliente();
	final var cidade = new Cidade();
	if (tipo_telaa == TIPO_TELA.INSERE) {
	    cliente.setAtivo(chkAtivo.isSelected());

	    // INSERCAO DE CODIGO CIDADE APENAS
	    cidade.setCodigo(Integer.valueOf(txtCodCidade.getText()));

	    cliente.setCidade(cidade);

	    try {
		negCliente.inserir(cliente);
	    } catch (SQLException e) {
		System.out.println(e.getMessage());
	    }
	} else {
	    cliente.setAtivo(chkAtivo.isSelected());
	    cliente.setCodigo(Integer.valueOf(txtCodigo.getText()));
	    cliente.setCPF(txtCPF.getText());
	    cliente.setEndereco(txtEndereco.getText());
	    cliente.setNome(txtNome.getText());
	    cliente.setTelefone(txtTelefone.getText());
	    cidade.setCodigo(Cidade.CodCidadeGet());
	    cliente.setCidade(cidade);

	    try {
		negCliente.alterar(cliente);
	    } catch (SQLException e) {
		System.out.println(e.getMessage());
	    }
	}
    }

    @FXML
    void btnPesquisarCidade(ActionEvent event) {
	var abreTelaCidadeMenu = new ControladorMenuCidade();
	abreTelaCidadeMenu.abreTelaCidadeMenu(TIPO_TELA.CONSULTA);

	atualizaValorCidade();

    }

    private void atualizaValorCidade() {
	txtCodCidade.setText(String.valueOf(Cidade.CodCidadeGet()));
	txtCidade.setText(Cidade.NomeCidadeGet());
    }
}
