package apresentacao.insere;

import java.io.IOException;
import java.sql.SQLException;

import apresentacao.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;

import negocio.NegFuncionario;
import objeto.Funcionario;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public class ControladorInserirFuncionario {
    @FXML
    private TextField txtNomeFuncionario;

    @FXML
    private PasswordField txtSenhaFuncionario;
    @FXML
    private TextField txtFuncao;
    @FXML
    private TextField txtCodigo;

    @FXML
    private CheckBox chkAdm;

    @FXML
    private Button btnGravarFuncionario;
    private static TIPO_TELA tipo_telaa;

    public void abreTelaFuncionarioInsere(final TIPO_TELA tipo_tela, Funcionario funcionario) {
	tipo_telaa = tipo_tela;
	final var stage = new Stage();
	Parent root;
	final var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/insere/FuncionarioInsere.fxml"));
	    root = loader.load();
	    final var scene = new Scene(root);
	    new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
	    stage.setScene(scene);
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	    var controlador = (ControladorInserirFuncionario) loader.getController();
	    if (tipo_tela.equals(TIPO_TELA.ALTERA)) {

		controlador.btnGravarFuncionario.setText("Alterar");
		controlador.txtFuncao.setText(funcionario.getFuncao());
		controlador.txtNomeFuncionario.setText(funcionario.getNome());
		controlador.chkAdm.setSelected(funcionario.getAdministrador());
		controlador.txtCodigo.setText(String.valueOf(funcionario.getCodigo()));
		stage.setTitle("Alterar Funcionario");
		stage.show();
	    } else {

		stage.setTitle("Inserir Funcionario");
		stage.show();
	    }
	} catch (IOException e) {
	    Alerta.alertaErro(e.getMessage());
	}
    }

    @FXML
    void btnGravarFuncionario(ActionEvent event) {
	final var negFun = new NegFuncionario();
	final var funcionario = new Funcionario();
	if (tipo_telaa == TIPO_TELA.INSERE) {
	    funcionario.setAdministrador(chkAdm.isSelected());
	    funcionario.setFuncao(txtFuncao.getText());
	    funcionario.setNome(txtNomeFuncionario.getText());
	    funcionario.setSenha(txtSenhaFuncionario.getText());
	    try {
		negFun.inserir(funcionario);

	    } catch (SQLException e) {
		Alerta.alertaErro(e.getMessage());
	    }
	} else {
	    funcionario.setCodigo(Integer.valueOf(txtCodigo.getText()));
	    funcionario.setAdministrador(chkAdm.isSelected());
	    funcionario.setFuncao(txtFuncao.getText());
	    funcionario.setNome(txtNomeFuncionario.getText());
	    if (txtSenhaFuncionario.getText().strip().isBlank())
		funcionario.setSenha("");
	    else
		funcionario.setSenha(txtSenhaFuncionario.getText());
	    try {
		negFun.alterar(funcionario);

	    } catch (SQLException e) {
		Alerta.alertaErro(e.getMessage());
	    }
	}
    }

}