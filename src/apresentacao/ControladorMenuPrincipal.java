package apresentacao;

import java.sql.SQLException;

import apresentacao.relatorios.RelatorioDois;
import apresentacao.relatorios.RelatorioQuatro;
import apresentacao.relatorios.RelatorioTres;
import apresentacao.relatorios.RelatorioUm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.sf.jasperreports.engine.JRException;
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

	@FXML
	void btnRelatorio(final ActionEvent event) {
		try {
			// ESTOQUE DE PRODUTO
			new RelatorioUm().gerar();
		} catch (JRException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void btnRelatorio2(final ActionEvent event) {
		try {

			// VENDAS POR CLIENTE
			new RelatorioDois().gerar();
		} catch (JRException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void btnRelatorio3(final ActionEvent event) {
		try {
			// CLIENTE COM MAIS VENDAS
			new RelatorioTres().gerar();
		} catch (JRException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void btnRelatorio4(final ActionEvent event) {
		try {
			// RELATORIO MESTRE EM DETALHES DE VENDAS
			new RelatorioQuatro().gerar();
		} catch (JRException | SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
