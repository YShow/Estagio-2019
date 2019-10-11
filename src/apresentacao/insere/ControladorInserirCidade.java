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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegCidade;
import objeto.Cidade;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public final class ControladorInserirCidade {
	@FXML
	private TextField txtCidade;
	@FXML
	private TextField txtCodigo;
	@FXML
	private Button btnGravar;

	@FXML
	private TextField txtEstado;
	private static TIPO_TELA tipo_telaa;

	public void abreTelaCidadeInsere(final TIPO_TELA tipo_tela, final Cidade cidade) {

		try {
			tipo_telaa = tipo_tela;
			final var stage = new Stage();

			final var loader = new FXMLLoader();
			stage.initModality(Modality.APPLICATION_MODAL);
			loader.setLocation(getClass().getResource("/apresentacao/insere/CidadeInsere.fxml"));
			final Parent root = loader.load();
			final var scene = new Scene(root);
			new JMetro(scene, Main.style);
			stage.setScene(scene);
			final ControladorInserirCidade controlador = loader.getController();
			controlador.txtCodigo.setVisible(false);
			if (tipo_tela.equals(TIPO_TELA.ALTERA)) {
				controlador.btnGravar.setText("Alterar");
				controlador.txtCidade.setText(cidade.getNome());
				controlador.txtEstado.setText(cidade.getEstado());
				controlador.txtCodigo.setText(String.valueOf(cidade.getCodigo()));
				stage.setTitle("Alterar Cidade");
				stage.show();
			} else if (tipo_tela.equals(TIPO_TELA.INSERE)) {
				stage.setTitle("Inserir Cidade");
				stage.show();

			}
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}

	}

	@FXML
	private void btnGravar(final ActionEvent event) {
		try {
			final var negcidade = new NegCidade();

			if (tipo_telaa == TIPO_TELA.INSERE) {
				if (verificaValores() && negcidade.inserir(pegaCidade())) {
					Alerta.alertaSucesso();
					btnGravar.getScene().getWindow().hide();
				}
			} else {
				if (verificaValores() && negcidade.alterar(pegaCidade())) {
					Alerta.alertaSucesso();
					btnGravar.getScene().getWindow().hide();
				}
			}
		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}

	}

	private Cidade pegaCidade() {
		final var cidade = new Cidade();
		if(!txtCodigo.getText().isBlank()) {
			cidade.setCodigo(Integer.valueOf(txtCodigo.getText().trim()));
		}
		cidade.setEstado(txtEstado.getText().trim());
		cidade.setNome(txtCidade.getText().trim());
		return cidade;
	}

	private boolean verificaValores() {
		final var erros = new StringBuilder();
		if (txtCidade.getText().isBlank()) {
			erros.append("Cidade não preenchida. \n");
		}
		if (txtEstado.getText().isBlank()) {
			erros.append("Estado não preenchido.");
		}
		if (erros.length() != 0) {
			Alerta.alertaCampoNulo(erros.toString());
		}
		return erros.length() == 0;
	}
}
