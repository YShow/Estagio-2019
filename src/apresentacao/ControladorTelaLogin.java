package apresentacao;

import java.io.IOException;
import java.sql.SQLException;

import apresentacao.insere.ControladorInserirFuncionario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegLogin;
import objeto.Funcionario;
import utilidade.Alerta;
import utilidade.Imagem;
import utilidade.Imagem.IMAGEM;
import utilidade.TIPO_TELA;

public final class ControladorTelaLogin extends Application {
	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtSenha;
	private static Stage stage;

	private double xOffset = 0;
	private double yOffset = 0;
	private final NegLogin login = new NegLogin();

	@FXML
	private void btnLogin(final ActionEvent event) {
		if (txtUsuario.getText().trim().isBlank() && txtSenha.getText().trim().isBlank()) {
			Alerta.alertaCampoNulo();
		} else {
			final var funcionario = new Funcionario();
			funcionario.setUsuario(txtUsuario.getText().trim());
			funcionario.setSenha(txtSenha.getText().trim());

			try {
				if (login.verificaLogin(funcionario)) {
					// fexa tela de login login
					stage.close();
					// abre tela do menu apos login
					abreTelaMenuPrincipal();
				} else {
					Alerta.alertaCampoNulo();
				}
			} catch (final SQLException e) {
				Alerta.alertaErro(e.getMessage());

			}
		}

	}

	@Override
	public void start(final Stage primaryStage) throws Exception {

		stage = primaryStage;
		try {
			if (login.ePrimeiroLogin()) {
				abreTelaLogin();
			} else {
				Alerta.alertaCustom();
				final var criausuario = new ControladorInserirFuncionario();
				criausuario.abreTelaFuncionarioInsere(TIPO_TELA.INSERE, null);
				if (login.ePrimeiroLogin()) {
					abreTelaLogin();
				}
			}
		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	private void abreTelaLogin() {

		try {

			final var loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));

			final Pane root = loader.load();

			final var scene = new Scene(root);

			new JMetro(scene, Main.style);
			root.setBackground(Imagem.colocaImagemFundo(IMAGEM.FUNDO));
			stage.getIcons().add(new Image(IMAGEM.FUNDO.getImagem()));
			stage.setResizable(false);
			final ControladorTelaLogin control = loader.getController();

			control.txtSenha.setOnKeyPressed(e -> {
				if(e.getCode().equals(KeyCode.ENTER))
				{
					control.btnLogin(null);
				}
			});
			control.txtUsuario.setOnKeyPressed(e -> {
				if(e.getCode().equals(KeyCode.ENTER))
				{
					control.btnLogin(null);
				}
			});
			stage.setTitle("Canta Galo");
			stage.setScene(scene);
			stage.show();

		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}

	}

	private void abreTelaMenuPrincipal() {
		try {
			final Pane root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));

			final var scene = new Scene(root);
			new JMetro(scene, Main.style);
			root.setBackground(Imagem.colocaImagemFundo(IMAGEM.FUNDO));
			root.setOnMousePressed(event -> {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			});

			// move around here
			root.setOnMouseDragged(event -> {
				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
			});

			stage.setTitle("Canta Galo");
			stage.setScene(scene);
			stage.show();
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}
}
