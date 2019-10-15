package apresentacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utilidade.TIPO_TELA;

public final class ControladorMenuPrincipal {

	private final ControladorMenuCaixa telaCaixa = new ControladorMenuCaixa();
	private final ControladorMenuCidade telaCidade = new ControladorMenuCidade();
	private final ControladorMenuCliente telaCliente = new ControladorMenuCliente();
	private final ControladorMenuFuncionario telaFuncionario = new ControladorMenuFuncionario();
	private final ControladorMenuProduto telaProduto = new ControladorMenuProduto();
	private final ControladorMenuVenda telaVenda = new ControladorMenuVenda();

	@FXML
	private void menuTelaCaixa(final ActionEvent event) {
		telaCaixa.abreTelaCaixaMenu();
	}

	@FXML
	private void menuTelaCidade(final ActionEvent event) {
		telaCidade.abreTelaCidadeMenu(TIPO_TELA.EXCLUI);
	}

	@FXML
	private void menuTelaCliente(final ActionEvent event) {
		telaCliente.abreTelaClienteMenu(TIPO_TELA.EXCLUI);
	}

	@FXML
	private void menuTelaFuncionario(final ActionEvent event) {
		telaFuncionario.abreTelaFuncionarioMenu();
	}

	@FXML
	private void menuTelaProduto(final ActionEvent event) {
		telaProduto.abreTelaProdutoMenu(TIPO_TELA.EXCLUI);
	}

	@FXML
	private void menuTelaVenda(final ActionEvent event) {
		telaVenda.abreTelaVendaMenu();
	}
}
