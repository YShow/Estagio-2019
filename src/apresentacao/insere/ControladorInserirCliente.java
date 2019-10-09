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
import negocio.NegCliente;
import objeto.Cidade;
import objeto.Cliente;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public final class ControladorInserirCliente {
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

	public void abreTelaClienteInsere(final TIPO_TELA tipo_tela, final Cliente cliente) {

		try {
			tipo_telaa = tipo_tela;
			final var stage = new Stage();
			final var loader = new FXMLLoader();
			stage.initModality(Modality.APPLICATION_MODAL);
			loader.setLocation(getClass().getResource("/apresentacao/insere/ClienteInsere.fxml"));
			final Parent root = loader.load();

			final var scene = new Scene(root);
			new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
			stage.setScene(scene);
			final ControladorInserirCliente controlador = loader.getController();
			if (tipo_tela.equals(TIPO_TELA.ALTERA)) {
				controlador.btnGrava.setText("Alterar");
				controlador.chkAtivo.setSelected(cliente.getAtivo());
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
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnGrava(final ActionEvent event) {
		final var negCliente = new NegCliente();
		final var cliente = new Cliente();
		final var cidade = new Cidade();
		if (tipo_telaa == TIPO_TELA.INSERE) {
			cliente.setAtivo(chkAtivo.isSelected());

			// INSERCAO DE CODIGO CIDADE APENAS
			cidade.setCodigo(Integer.parseInt(txtCodCidade.getText().trim()));
			cliente.setNome(txtNome.getText().trim());
			cliente.setEndereco(txtEndereco.getText().trim());
			cliente.setTelefone(txtTelefone.getText().trim());
			cliente.setCPF(txtCPF.getText().trim());
			cliente.setCidade(cidade);

			try {
				if (negCliente.inserir(cliente)) {
					Alerta.alertaSucesso();
					btnGrava.getScene().getWindow().hide();
				}
			} catch (final SQLException e) {
				Alerta.alertaErro(e.getMessage());
			}
		} else {
			cliente.setAtivo(chkAtivo.isSelected());
			cliente.setCodigo(Integer.parseInt(txtCodigo.getText().trim()));
			cliente.setCPF(txtCPF.getText().trim());
			cliente.setEndereco(txtEndereco.getText().trim());
			cliente.setNome(txtNome.getText().trim());
			cliente.setTelefone(txtTelefone.getText().trim());
			cidade.setCodigo(Integer.parseInt(txtCodCidade.getText().trim()));
			cliente.setCidade(cidade);

			try {
				if (negCliente.alterar(cliente)) {
					Alerta.alertaSucesso();
					btnGrava.getScene().getWindow().hide();
				}

			} catch (final SQLException e) {
				Alerta.alertaErro(e.getMessage());
			}
		}
	}

	@FXML
	private void btnPesquisarCidade(final ActionEvent event) {
		final var abreTelaCidadeMenu = new ControladorMenuCidade();
		final var cidade = abreTelaCidadeMenu.abreTelaCidadeMenu(TIPO_TELA.CONSULTA);

		atualizaValorCidade(cidade);

	}

	private void atualizaValorCidade(final Cidade cidade) {
		txtCodCidade.setText(String.valueOf(cidade.getCodigo()));
		txtCidade.setText(cidade.getNome());
	}
}
