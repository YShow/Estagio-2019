package apresentacao.insere;

import java.io.IOException;
import java.sql.SQLException;

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
import negocio.NegFuncionario;
import objeto.Funcionario;
import utilidade.TIPO_TELA;

public class ControladorInserirFuncionario {
    @FXML
    private TextField txtNomeFuncionario;

    @FXML
    private PasswordField txtSenhaFuncionario;
    @FXML
    private TextField txtFuncao;

    @FXML
    private CheckBox chkAdm;

    @FXML
    private Button btnGravarFuncionario;
    private static TIPO_TELA tipo_telaa;

    public void abreTelaFuncionarioInsere(final TIPO_TELA tipo_tela) {
	tipo_telaa = tipo_tela;
	var stage = new Stage();
	Parent root;
	var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/insere/FuncionarioInsere.fxml"));
	    root = loader.load();
	    stage.setScene(new Scene(root, 600, 450));
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	    if (tipo_tela.equals(TIPO_TELA.ALTERA)) {
		var controlador = (ControladorInserirFuncionario) loader.getController();
		controlador.btnGravarFuncionario.setText("Alterar");
		stage.setTitle("Alterar Funcionario");
		stage.show();
	    } else {
		stage.setTitle("Inserir Funcionario");
		stage.show();
	    }
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    @FXML
    void btnGravarFuncionario(ActionEvent event) {
	if(tipo_telaa == TIPO_TELA.INSERE)
	{
	    System.out.println("executando");
	    Funcionario funcionario = new Funcionario();
	    funcionario.setAdministrador(chkAdm.isPressed());
	    funcionario.setFuncao(txtFuncao.getText());
	    funcionario.setNome(txtNomeFuncionario.getText());
	    funcionario.setSenha(txtSenhaFuncionario.getText());
	    
	    NegFuncionario insere  = new NegFuncionario();
	    try {
		insere.inserir(funcionario);
	    } catch (SQLException e) {		
		System.out.println(e.getMessage());
	    }
	}
    }

}