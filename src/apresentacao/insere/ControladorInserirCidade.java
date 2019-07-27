package apresentacao.insere;

import java.io.IOException;
import java.sql.SQLException;

import utilidade.TIPO_TELA;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import negocio.NegCidade;
import objeto.Cidade;

public class ControladorInserirCidade {
    @FXML
    private TextField txtCidade;

    @FXML
    private Button btnGravar;
    
    //POSSIVEL SOLUÇÃO
    //https://stackoverflow.com/questions/34282973/javafx-fxml-add-choices-to-choice-box
    @FXML
    private TextField txtEstado;
    private static TIPO_TELA tipo_telaa;


    public void abreTelaCidadeInsere(final TIPO_TELA tipo_tela) {
	
	tipo_telaa = tipo_tela;
	var stage = new Stage();
	Parent root;
	var loader = new FXMLLoader();
	stage.initModality(Modality.APPLICATION_MODAL);

	try {
	    loader.setLocation(getClass().getResource("/apresentacao/insere/CidadeInsere.fxml"));
	    root = loader.load();
	    stage.setScene(new Scene(root, 600, 450));
	    stage.setMinHeight(root.minHeight(-1));
	    stage.setMinWidth(root.minWidth(-1));
	   
	    if (tipo_tela.equals(TIPO_TELA.ALTERA)) {
		var controlador = (ControladorInserirCidade) loader.getController();
		controlador.btnGravar.setText("Alterar");
		stage.setTitle("Alterar Cidade");
		stage.show();
	    } else if (tipo_tela.equals(TIPO_TELA.INSERE)) {
		stage.setTitle("Inserir Cidade");
		stage.show();

	    }
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}

    }

    @FXML
    void btnGravar(ActionEvent event) {
	if(tipo_telaa == TIPO_TELA.INSERE)
	{
	    Cidade cidade = new Cidade();
	    cidade.setNome(txtCidade.getText());
	    cidade.setEstado(txtEstado.getText());
	    NegCidade negcidade = new NegCidade();
	   try {
	    negcidade.inserir(cidade);
	} catch (SQLException e) {
	   System.out.println(e.getMessage());
	}
	}
    }
}
