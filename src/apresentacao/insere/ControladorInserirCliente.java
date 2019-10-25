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
	private final NegCliente negCliente = new NegCliente();

	public void abreTelaClienteInsere(final TIPO_TELA tipo_tela, final Cliente cliente) {

		try {
			tipo_telaa = tipo_tela;
			final var stage = new Stage();
			final var loader = new FXMLLoader();
			stage.initModality(Modality.APPLICATION_MODAL);
			loader.setLocation(getClass().getResource("/apresentacao/insere/ClienteInsere.fxml"));
			final Parent root = loader.load();

			final var scene = new Scene(root);
			new JMetro(scene, Main.style);
			stage.setScene(scene);
			final ControladorInserirCliente controlador = loader.getController();
			if (tipo_tela.equals(TIPO_TELA.ALTERA)) {
				controlador.btnGrava.setText("Alterar");
				controlador.chkAtivo.setSelected(cliente.isAtivo());
				controlador.txtCidade.setText(cliente.getCidade().getNome());
				controlador.txtCodigo.setText(String.valueOf(cliente.getCodigo()));
				controlador.txtCPF.setText(cliente.getCpf());
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
		try {
			if (tipo_telaa == TIPO_TELA.INSERE) {
				if (verificaValores() && negCliente.inserir(pegaCliente())) {
					Alerta.alertaSucesso();
					btnGrava.getScene().getWindow().hide();
				}
			} else {
				if (verificaValores() && negCliente.alterar(pegaCliente())) {
					Alerta.alertaSucesso();
					btnGrava.getScene().getWindow().hide();
				}
			}
		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}

	}

	private Cliente pegaCliente() {
		final var cliente = new Cliente();
		final var cidade = new Cidade();
		cliente.setAtivo(chkAtivo.isSelected());
		if (!txtCodigo.getText().isBlank()) {
			cliente.setCodigo(Integer.parseInt(txtCodigo.getText().trim()));
		}
		cliente.setCpf(txtCPF.getText().trim());
		cliente.setEndereco(txtEndereco.getText().trim());
		cliente.setNome(txtNome.getText().trim());
		cliente.setTelefone(txtTelefone.getText().trim());
		cidade.setCodigo(Integer.parseInt(txtCodCidade.getText().trim()));
		cliente.setCidade(cidade);
		return cliente;
	}

	@FXML
	private void btnPesquisarCidade(final ActionEvent event) {
		final var abreTelaCidadeMenu = new ControladorMenuCidade();
		final var cidade = abreTelaCidadeMenu.abreTelaCidadeMenu(TIPO_TELA.CONSULTA);

		atualizaValorCidade(cidade);

	}

	private boolean verificaValores() {
		final var erros = new StringBuilder();
		if (txtCidade.getText().isBlank()) {
			erros.append("Cidade esta vazio. \n");
		}
		if (txtCodCidade.getText().isBlank()) {
			erros.append("Codigo da cidade esta vazio. \n");
		}
		if (txtCPF.getText().isBlank()) {
			erros.append("CPF esta vazio. \n");
		}
		if (txtEndereco.getText().isBlank()) {
			erros.append("Endereço esta vazio. \n");
		}
		if (txtNome.getText().isBlank()) {
			erros.append("Nome esta vazio. \n");
		}
		if (txtTelefone.getText().isBlank()) {
			erros.append("Telefone esta vazio.");
		}
		if (erros.length() != 0) {
			Alerta.alertaCampoNulo(erros.toString());
		}
		return erros.length() == 0;
	}

	private void atualizaValorCidade(final Cidade cidade) {
		txtCodCidade.setText(String.valueOf(cidade.getCodigo()));
		txtCidade.setText(cidade.getNome());
	}
}
