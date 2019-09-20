package apresentacao;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import apresentacao.insere.ControladorInserirVenda;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import negocio.NegVendas;
import objeto.Vendas;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public final class ControladorMenuVenda {
	private final ControladorInserirVenda tela = new ControladorInserirVenda();
	@FXML
	private TableView<Vendas> tvVenda;
	@FXML
	private TextField txtVenda;

	@FXML
	private TableColumn<Vendas, Integer> tcCodigo;

	@FXML
	private TableColumn<Vendas, Integer> tcCodCliente;

	@FXML
	private TableColumn<Vendas, Integer> tcCodCaixa;

	@FXML
	private TableColumn<Vendas, String> tcDataVenda;

	@FXML
	private TableColumn<Vendas, String> tcFormaPAg;

	@FXML
	private TableColumn<Vendas, Boolean> tcAtivo;

	@FXML
	private void btnAlteraVenda(final ActionEvent event) {
		final var venda = tvVenda.getSelectionModel().getSelectedItem();
		tela.abreTelaVendaInsere(TIPO_TELA.ALTERA, venda);
	}

	@FXML
	private void btnConsultaVenda(final ActionEvent event) {
		final var negVenda = new NegVendas();
		try {
			final List<Vendas> venda = negVenda.consultar(txtVenda.getText());

			final var data = FXCollections.observableList(venda);
			tvVenda.setItems(data);
			tcCodigo.setCellValueFactory(cod -> new ReadOnlyIntegerWrapper(cod.getValue().getCodigo()).asObject());
			tcFormaPAg
					.setCellValueFactory(formPag -> new ReadOnlyStringWrapper(formPag.getValue().getFormaPagamento()));

			tcDataVenda.setCellValueFactory(dataVenda -> new ReadOnlyStringWrapper(
					dataVenda.getValue().getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));

			tcCodCaixa.setCellValueFactory(
					codCaixa -> new ReadOnlyIntegerWrapper(codCaixa.getValue().getCaixa().getCodigo()).asObject());

			tcCodCliente.setCellValueFactory(
					codCliente -> new ReadOnlyIntegerWrapper(codCliente.getValue().getCliente().getCodigo())
							.asObject());

			tcAtivo.setCellValueFactory(ativo -> new ReadOnlyBooleanWrapper(ativo.getValue().isAtivo()));

		} catch (final SQLException e) {

			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnDesativaVenda(final ActionEvent event) {
		final var venda = tvVenda.getSelectionModel().getSelectedItem().getCodigo();
		final var negVenda = new NegVendas();
		try {
			if (negVenda.excluir(venda)) {
				tvVenda.getItems().remove(venda);
				Alerta.alertaSucesso();
			}

		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnInsereVenda(final ActionEvent event) {
		tela.abreTelaVendaInsere(TIPO_TELA.INSERE, null);
	}

}
