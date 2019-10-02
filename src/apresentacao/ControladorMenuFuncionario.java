package apresentacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import apresentacao.insere.ControladorInserirFuncionario;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegFuncionario;
import objeto.Funcionario;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public final class ControladorMenuFuncionario {

	@FXML
	private TextField txtFuncionario;
	@FXML
	private TableView<Funcionario> tblFuncionario;

	@FXML
	private TableColumn<Funcionario, Integer> tcCodigo;

	@FXML
	private TableColumn<Funcionario, String> tcNome;

	@FXML
	private TableColumn<Funcionario, String> tcFuncao;

	@FXML
	private TableColumn<Funcionario, String> tcAdm;
	@FXML
	private TableColumn<Funcionario, String> tcUsuario;

    @FXML
    private Button btnDesativaFuncionario;

	private final ControladorInserirFuncionario tela = new ControladorInserirFuncionario();

	public void abreTelaFuncionarioMenu() {



		try {
			final var stage = new Stage();
			final var loader = new FXMLLoader();
			stage.initModality(Modality.APPLICATION_MODAL);
			loader.setLocation(getClass().getResource("/apresentacao/Funcionario.fxml"));
			final Parent root =  loader.load();
			final ControladorMenuFuncionario control = loader.getController();
			final var scene = new Scene(root);
			new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
			stage.setScene(scene);
			if(!Funcionario.getFuncionario().getAdministrador())
			{
				control.btnDesativaFuncionario.setDisable(true);
			}

			stage.show();

			stage.setOnCloseRequest(e -> {

				control.limpaTabela();

			});
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnAlteraFuncionario(final ActionEvent event) {
		final var funcionario = tblFuncionario.getSelectionModel().getSelectedItem();

		tela.abreTelaFuncionarioInsere(TIPO_TELA.ALTERA, funcionario);
	}

	private void limpaTabela() {
		tblFuncionario.getItems().clear();
	}

	@FXML
	private void btnConsultaFuncionario(final ActionEvent event) {
		limpaTabela();
		final var negFuncionario = new NegFuncionario();
		try {
			final List<Funcionario> funcionario = negFuncionario.consultar(txtFuncionario.getText());
			final var data = FXCollections.observableList(funcionario);
			tblFuncionario.setItems(data);
			tcCodigo.setCellValueFactory(new PropertyValueFactory<Funcionario,Integer>("Codigo"));
			tcNome.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("Nome"));
			tcFuncao.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("Funcao"));
			tcUsuario.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("Usuario"));
			tcAdm.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("Administrador"));

		} catch (final SQLException e) {

			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnDesativaFuncionario(final ActionEvent event) {
		final var negFuncionario = new NegFuncionario();
		final var funcionario = tblFuncionario.getSelectionModel().getSelectedItem();
		try {
			if (negFuncionario.excluir(funcionario.getCodigo())) {
				tblFuncionario.getItems().remove(funcionario);
				Alerta.alertaSucesso();
			}
		} catch (final SQLException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	private void btnInsereFuncionario(final ActionEvent event) {
		tela.abreTelaFuncionarioInsere(TIPO_TELA.INSERE, null);
	}
}
