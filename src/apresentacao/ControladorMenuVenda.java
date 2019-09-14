package apresentacao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import apresentacao.insere.ControladorInserirVenda;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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
	var venda = tvVenda.getSelectionModel().getSelectedItem();
	tela.abreTelaVendaInsere(TIPO_TELA.ALTERA, venda);
    }

    @FXML
    void btnConsultaVenda(ActionEvent event) {
	final var negVenda = new NegVendas();
	try {
	    List<Vendas> venda = negVenda.consultar(txtVenda.getText());

	    var data = FXCollections.observableList(venda);
	    tvVenda.setItems(data);
	    tcCodigo.setCellValueFactory(new PropertyValueFactory("Codigo"));
	    tcFormaPAg.setCellValueFactory(new PropertyValueFactory("FormaPagamento"));
	    tcDataVenda.setCellValueFactory(new PropertyValueFactory("Data"));

	    tcCodCaixa.setCellValueFactory(
		    new Callback<TableColumn.CellDataFeatures<Vendas, Integer>, ObservableValue<Integer>>() {

			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Vendas, Integer> param) {

			    return new ReadOnlyObjectWrapper<>(param.getValue().getCaixa().getCodigo());
			}
		    });

	    tcCodCliente.setCellValueFactory(
		    new Callback<TableColumn.CellDataFeatures<Vendas, Integer>, ObservableValue<Integer>>() {

			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Vendas, Integer> param) {

			    return new ReadOnlyObjectWrapper<>(param.getValue().getCliente().getCodigo());
			}
		    });

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
