package apresentacao;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public final class ControladorMenuPrincipal {

	private final Stage stage = new Stage();
	private Parent root;

	@FXML
	private void menuTelaCaixa(final ActionEvent event) {

		try {

			root = FXMLLoader.load(getClass().getResource("Caixa.fxml"));
			stage.setTitle("Menu de Caixa");
			final var scene = new Scene(root);
			new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
			stage.setScene(scene);
			stage.show();
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void menuTelaCidade(final ActionEvent event) {
		final var telaCidade = new ControladorMenuCidade();
		telaCidade.abreTelaCidadeMenu(TIPO_TELA.EXCLUI);
	}

	@FXML
	private void menuTelaCliente(final ActionEvent event) {

		final var telaCliente = new ControladorMenuCliente();
		telaCliente.abreTelaClienteMenu(TIPO_TELA.EXCLUI);
	}

	@FXML
	private void menuTelaFuncionario(final ActionEvent event) {

		final var telaFuncionario = new ControladorMenuFuncionario();
		telaFuncionario.abreTelaFuncionarioMenu();
	}

	@FXML
	private void menuTelaProduto(final ActionEvent event) {

		final var telaProduto = new ControladorMenuProduto();
		telaProduto.abreTelaProdutoMenu(TIPO_TELA.EXCLUI);

	}

	@FXML
	private void menuTelaVenda(final ActionEvent event) {

		try {
			root = FXMLLoader.load(getClass().getResource("Venda.fxml"));

			stage.setTitle("Menu de Venda");
			final var scene = new Scene(root);
			new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
			stage.setScene(scene);
			stage.show();

		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}
}
