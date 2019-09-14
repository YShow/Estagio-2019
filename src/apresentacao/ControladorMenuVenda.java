package apresentacao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import apresentacao.insere.ControladorInserirVenda;
import javafx.beans.property.ReadOnlyIntegerWrapper;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import negocio.NegVendas;
import objeto.Vendas;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public class ControladorMenuVenda {
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
    private TableColumn<Vendas, LocalDate> tcDataVenda;

    @FXML
    private TableColumn<Vendas, String> tcFormaPAg;

    @FXML
    void btnAlteraVenda(ActionEvent event) {
	final var venda = tvVenda.getSelectionModel().getSelectedItem();
	tela.abreTelaVendaInsere(TIPO_TELA.ALTERA, venda);
    }

    @FXML
    void btnConsultaVenda(ActionEvent event) {
	final var negVenda = new NegVendas();
	try {
	    final List<Vendas> venda = negVenda.consultar(txtVenda.getText());

	    final var data = FXCollections.observableList(venda);
	    tvVenda.setItems(data);
	    tcCodigo.setCellValueFactory(new PropertyValueFactory("Codigo"));
	    tcFormaPAg.setCellValueFactory(new PropertyValueFactory("FormaPagamento"));
	    tcDataVenda.setCellValueFactory(new PropertyValueFactory("Data"));

	    tcCodCaixa.setCellValueFactory(codCaixa -> new ReadOnlyIntegerWrapper(codCaixa.getValue().getCaixa().getCodigo()).asObject());

	    tcCodCliente.setCellValueFactory(codCliente -> new ReadOnlyIntegerWrapper(codCliente.getValue().getCliente().getCodigo()).asObject());

	} catch (SQLException e) {

	    Alerta.alertaErro(e.getMessage());
	}
    }

    @FXML
    void btnDesativaVenda(ActionEvent event) {

    }

    @FXML
    void btnInsereVenda(ActionEvent event) {
	tela.abreTelaVendaInsere(TIPO_TELA.INSERE, null);
    }

}
