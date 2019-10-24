package apresentacao.insere;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import apresentacao.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegCaixa;
import objeto.Caixa;
import objeto.Funcionario;
import utilidade.Alerta;

public class ControladorInserirCaixa {
	@FXML
    private TextField txtData;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtSaida;

    @FXML
    private TextField txtFuncionario;
    @FXML
    private Button btnCancelar;
    public void abreTelaCaixaMenu() {
		try {
			final var stage = new Stage();

			final var loader = new FXMLLoader(getClass().getResource("/apresentacao/insere/CaixaInsere.fxml"));
			stage.initModality(Modality.APPLICATION_MODAL);

			final Parent root = loader.load();
			stage.setTitle("Inserir Caixa");
			final var scene = new Scene(root);
			final ControladorInserirCaixa control = loader.getController();

			control.txtData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			control.txtFuncionario.setText(String.valueOf(Funcionario.getFuncionario().getCodigo()));

			new JMetro(scene, Main.style);
			stage.setScene(scene);
			stage.show();
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}


    @FXML
    void btnCancelar(final ActionEvent event) {
    	btnCancelar.getScene().getWindow().hide();
    }

    @FXML
    void btnInserir(final ActionEvent event) {
    	final var negCaixa = new NegCaixa();
    	final var caixa = new Caixa();

    	caixa.setAtivo(true);
    	caixa.setData(LocalDate.now());
    	caixa.setFuncionario(Funcionario.getFuncionario().getCodigo());
    	caixa.setPrecototal(Double.parseDouble(txtValor.getText()));
    	caixa.setSaida(Double.parseDouble(txtSaida.getText()));
    	try {

			if(negCaixa.inserir(caixa))
			{
				Alerta.alertaSucesso();
			}
		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}
    }
}
