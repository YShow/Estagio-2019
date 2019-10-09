package apresentacao.insere;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.LocalDate;

import apresentacao.ControladorMenuCliente;
import apresentacao.ControladorMenuProduto;
import apresentacao.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegVendas;
import objeto.Caixa;
import objeto.Cliente;
import objeto.Produto;
import objeto.Vendas;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public final class ControladorInserirVenda {
	@FXML
	private TextField txtCliente;

	@FXML
	private TextField txtProduto;

	@FXML
	private TextField txtQtdEstoque;

	@FXML
	private TextField txtQtd;

	@FXML
	private TextField txtPrecoUnitario;

	@FXML
	private TextField txtPrecoTotal;

	@FXML
	private Button btnGravar;
	@FXML
	private TextField txtFormaPagamento;

	@FXML
	private TextField txtSaida;
	@FXML
	private CheckBox chkAtivo;
	private static TIPO_TELA tipo_telaa;

	public void abreTelaVendaInsere(final TIPO_TELA tipo_tela, final Vendas venda) {

		try {
			tipo_telaa = tipo_tela;
			final var stage = new Stage();
			final var loader = new FXMLLoader();
			stage.initModality(Modality.APPLICATION_MODAL);

			loader.setLocation(getClass().getResource("/apresentacao/insere/VendaInsere.fxml"));
			final Parent root = loader.load();
			final var scene = new Scene(root);
			new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
			stage.setScene(scene);

			final ControladorInserirVenda controlador = loader.getController();

			if (tipo_tela.equals(TIPO_TELA.ALTERA)) {

				controlador.txtCliente.setText(String.valueOf(venda.getCliente().getCodigo()));
				preencheCampos(controlador, venda.getCodigo());
				stage.setTitle("Alterar Venda");
				stage.show();
			} else if (tipo_tela.equals(TIPO_TELA.INSERE)) {
				formataCampo(controlador);
				stage.setTitle("Inserir Venda");
				stage.show();
			}
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnPesquisaCliente(final ActionEvent event) {
		final var telaCliente = new ControladorMenuCliente();
		final var cliente = telaCliente.abreTelaClienteMenuAlterar(TIPO_TELA.CONSULTA);
		atualizaValorCliente(cliente);
	}

	@FXML
	private void btnPesquisaProduto(final ActionEvent event) {
		final var telaProduto = new ControladorMenuProduto();
		final var produto = telaProduto.abreTelaProdutoMenuAlterar(TIPO_TELA.CONSULTA);
		atualizaValorProduto(produto);
	}

	private void atualizaValorCliente(final Cliente cliente) {

		txtCliente.setText(String.valueOf(cliente.getCodigo()));
	}

	private void atualizaValorProduto(final Produto produto) {
		txtProduto.setText(String.valueOf(produto.getCodigo()));
		txtQtdEstoque.setText(String.valueOf(produto.getQuantidade()));
		txtPrecoUnitario.setText(String.valueOf(produto.getPreco()));
	}

	private void formataCampo(final ControladorInserirVenda controlador) {
		controlador.txtQtd.setTextFormatter(new TextFormatter<String>(change -> {
			if (!change.getText().isBlank()) {
				final var too = new Tooltip();
				if (Integer.parseInt(change.getControlNewText()) > Integer
						.parseInt(controlador.txtQtdEstoque.getText())) {
					too.setText("Quantidade maior do que em estoque");
					too.setShowDuration(Duration.seconds(3));
					change.getControl().setTooltip(too);
					change.getControl().getTooltip().show(change.getControl().getScene().getWindow());

					return null;
				} else {
					if (!change.getControlNewText().isBlank()) {
						final var qtd = Integer.parseInt(change.getControlNewText());
						final var preco = Double.parseDouble(controlador.txtPrecoUnitario.getText());
						final var total = qtd * preco;
						final var valor = BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_EVEN);
						controlador.txtPrecoTotal.setText(valor.toString());
					}
					return change;
				}
			}
			return change;

		}));
	}

	private Vendas preencheVenda() {
		final var venda = new Vendas();
		final var cliente = new Cliente();
		final var produto = new Produto();
		final var caixa = new Caixa();

		// PEGA CODIGO DO CLIENTE
		cliente.setCodigo(Integer.parseInt(txtCliente.getText().trim()));

		// PEGA PRODUTO
		produto.setCodigo(Integer.parseInt(txtProduto.getText().trim()));
		produto.setPreco(Double.parseDouble(txtPrecoUnitario.getText().trim()));
		produto.setQuantidade(Integer.parseInt(txtQtd.getText().trim()));

		// PEGA CAIXA
		caixa.setAtivo(true);
		caixa.setCliente(Integer.parseInt(txtCliente.getText().trim()));
		caixa.setData(LocalDate.now());

		final var precototal = BigDecimal.valueOf(Double.parseDouble(txtPrecoTotal.getText().trim())).doubleValue();

		caixa.setPrecototal(precototal);
		caixa.setSaida(Double.parseDouble(txtSaida.getText().trim()));

		venda.setAtivo(true);
		venda.setData(LocalDate.now());

		venda.setCliente(cliente);
		venda.setFormaPagamento(txtFormaPagamento.getText().trim());

		venda.setProduto(produto);
		venda.setCaixa(caixa);

		return venda;
	}

	@FXML
	private void btnGravar(final ActionEvent event) {

		final var venda = preencheVenda();
		final var negVenda = new NegVendas();
		try {
			if (tipo_telaa.equals(TIPO_TELA.INSERE)) {
				if (negVenda.inserir(venda)) {
					Alerta.alertaSucesso();
					btnGravar.getScene().getWindow().hide();
				}
			} else {
				if (negVenda.alterar(venda)) {
					Alerta.alertaSucesso();
					btnGravar.getScene().getWindow().hide();
				}
			}
		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	private void preencheCampos(final ControladorInserirVenda controlador, final int idVenda) {

		try {
			final var negVenda = new NegVendas();
			final var venda = negVenda.pegaVendaAlterar(idVenda);
			controlador.txtFormaPagamento.setText(venda.getFormaPagamento());
			controlador.txtPrecoTotal.setText(String.valueOf(venda.getCaixa().getPrecototal()));
			controlador.txtPrecoUnitario.setText(String.valueOf(venda.getProduto().getPreco()));
			controlador.txtProduto.setText(String.valueOf(venda.getProduto().getCodigo()));
			controlador.txtQtd.setText(String.valueOf(venda.getProduto().getQuantidade()));
			controlador.chkAtivo.setSelected(venda.isAtivo());
		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}
}
