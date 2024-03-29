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
import utilidade.Senha;
import utilidade.TIPO_TELA;

public final class ControladorInserirFuncionario {
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
	private TextField txtUsuario;

	@FXML
	private Button btnGravarFuncionario;

	@FXML
	private CheckBox chkAtivo;
	private static TIPO_TELA tipo_telaa;
	private final NegFuncionario negFun = new NegFuncionario();

	public void abreTelaFuncionarioInsere(final TIPO_TELA tipo_tela, final Funcionario funcionario) {

		try {
			tipo_telaa = tipo_tela;
			final var stage = new Stage();

			final var loader = new FXMLLoader();
			stage.initModality(Modality.APPLICATION_MODAL);
			loader.setLocation(getClass().getResource("/apresentacao/insere/FuncionarioInsere.fxml"));
			final Parent root = loader.load();
			final var scene = new Scene(root);
			new JMetro(scene, Main.style);
			stage.setScene(scene);

			final ControladorInserirFuncionario controlador = loader.getController();
			if (tipo_tela.equals(TIPO_TELA.ALTERA)) {

				controlador.btnGravarFuncionario.setText("Alterar");
				controlador.txtFuncao.setText(funcionario.getFuncao());
				controlador.txtNomeFuncionario.setText(funcionario.getNome());
				controlador.chkAdm.setSelected(funcionario.isAdministrador());
				controlador.txtCodigo.setText(String.valueOf(funcionario.getCodigo()));
				controlador.txtUsuario.setText(funcionario.getUsuario());
				controlador.chkAtivo.setDisable(false);
				stage.setTitle("Alterar Funcionario");
				stage.show();
			} else {

				stage.setTitle("Inserir Funcionario");
				stage.showAndWait();
			}
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnGravarFuncionario(final ActionEvent event) {
		try {

			if (tipo_telaa == TIPO_TELA.INSERE) {
				if (verificaValores() && negFun.inserir(pegaValores())) {
					Alerta.alertaSucesso();
					btnGravarFuncionario.getScene().getWindow().hide();
				}
			} else {
				if (verificaValores() && negFun.alterar(pegaValores())) {
					Alerta.alertaSucesso();
					btnGravarFuncionario.getScene().getWindow().hide();
				}
			}
		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	private Funcionario pegaValores() {
		final var funcionario = new Funcionario();
		if (!txtCodigo.getText().isBlank()) {
			funcionario.setCodigo(Integer.parseInt(txtCodigo.getText()));
		}
		funcionario.setAdministrador(chkAdm.isSelected());
		funcionario.setFuncao(txtFuncao.getText().trim());
		funcionario.setNome(txtNomeFuncionario.getText().trim());
		if (!txtSenhaFuncionario.getText().isBlank()) {
			final var salt = Senha.geraSalt();
			funcionario.setSenha(Senha.criaSenha(txtSenhaFuncionario.getText().trim(), salt));
			funcionario.setSalt(salt);
		}
		funcionario.setUsuario(txtUsuario.getText().trim());
		funcionario.setAtivo(chkAtivo.isSelected());
		return funcionario;
	}

	private boolean verificaValores() {
		final var erros = new StringBuilder();
		if (txtFuncao.getText().isBlank()) {
			erros.append("Funcao esta vazio. \n");
		}
		if (txtNomeFuncionario.getText().isBlank()) {
			erros.append("Nome do funcionario esta vazio. \n");
		}
		if (txtSenhaFuncionario.getText().isBlank() && tipo_telaa.equals(TIPO_TELA.INSERE)) {
			erros.append("Senha esta vazio. \n");
		}
		if (txtUsuario.getText().isBlank()) {
			erros.append("Usuario esta vazio. \n");
		}
		if (erros.length() != 0) {
			Alerta.alertaCampoNulo(erros.toString());
		}

		return erros.length() == 0;
	}

}