package apresentacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utilidade.TIPO_TELA;

public final class ControladorMenuPrincipal {



	@FXML
	private void menuTelaCaixa(final ActionEvent event) {

		final var telaCaixa = new ControladorMenuCaixa();
		telaCaixa.abreTelaCaixaMenu();
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
		final var telaVenda = new ControladorMenuVenda();
		telaVenda.abreTelaVendaMenu();

	}
}
