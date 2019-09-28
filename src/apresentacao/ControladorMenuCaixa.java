package apresentacao;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import negocio.NegCaixa;
import objeto.Caixa;
import utilidade.Alerta;

public final class ControladorMenuCaixa {

    @FXML
    private DatePicker dpData;

	  @FXML
	    private TableView<Caixa> tvCaixa;

	    @FXML
	    private TableColumn<Caixa, Integer> tcCodigo;

	    @FXML
	    private TableColumn<Caixa, LocalDate> tcData;

	    @FXML
	    private TableColumn<Caixa, Double> tcPreco;

	    @FXML
	    private TableColumn<Caixa, Integer> tcSaida;

	    @FXML
	    private TableColumn<Caixa, Integer> tcCodCli;

	    @FXML
	    private TableColumn<Caixa, Boolean> tcAtivo;

	    @FXML
	    void btnAlterarCaixa(final ActionEvent event) {

	    }

	    @FXML
	    void btnDesativarCaixa(final ActionEvent event) {

	    }

	    @FXML
	    void btnInserirCaixa(final ActionEvent event) {

	    }

	    @FXML
	    void conulstaCaixa(final ActionEvent event) {
	    	final var negCaixa = new NegCaixa();
	    	try {
	    	final var caixas =	negCaixa.consultar(dpData.getValue());

	    	tcAtivo.setCellValueFactory(new PropertyValueFactory<Caixa,Boolean>("ativo"));
	    	tcCodCli.setCellValueFactory(new PropertyValueFactory<Caixa,Integer>("cliente"));
	    	tcCodigo.setCellValueFactory(new PropertyValueFactory<Caixa,Integer>("codigo"));
	    	tcData.setCellValueFactory(new PropertyValueFactory<Caixa,LocalDate>("data"));
	    	tcPreco.setCellValueFactory(new PropertyValueFactory<Caixa,Double>("precototal"));
	    	tcSaida.setCellValueFactory(new PropertyValueFactory<Caixa,Integer>("saida"));

	    	final var data = FXCollections.observableArrayList(caixas);
	    	tvCaixa.setItems(data);

	    	} catch (final SQLException e) {
				Alerta.alertaErro(e.getMessage());
			}


	    }
}
