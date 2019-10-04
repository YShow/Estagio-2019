package apresentacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import apresentacao.insere.ControladorInserirCliente;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import negocio.NegCliente;
import objeto.Cliente;
import objeto.Funcionario;
import utilidade.Alerta;
import utilidade.TIPO_TELA;

public final class ControladorMenuCliente {
	@FXML
	private TextField txtCliente;

	@FXML
	private Button btnDesativarCliente;

	@FXML
	private Button btnAlterarCliente;

	@FXML
	private Button btnInsereCliente;

	@FXML
	private TableView<Cliente> tvCliente;

	@FXML
	private TableColumn<Cliente, Integer> tcCodigo;

	@FXML
	private TableColumn<Cliente, String> tcNome;

	@FXML
	private TableColumn<Cliente, String> tcCPF;

	@FXML
	private TableColumn<Cliente, String> tcEndereco;

	@FXML
	private TableColumn<Cliente, String> tcTelefone;

	@FXML
	private TableColumn<Cliente, Boolean> tcAtivo;
	@FXML
	private TableColumn<Cliente, String> tcCidade;
	@FXML
	private TableColumn<Cliente, Integer> tcCodCidade;
	private final ControladorInserirCliente tela = new ControladorInserirCliente();
	private static TIPO_TELA tipo_telaa;
	private static Cliente clienteAlterar;
	private ControladorMenuCliente controlador;

	public void abreTelaClienteMenu(final TIPO_TELA tipo_tela) {

		try {
			tipo_telaa = tipo_tela;
			final var stage = new Stage();

			final var loader = new FXMLLoader();
			stage.initModality(Modality.APPLICATION_MODAL);
			loader.setLocation(getClass().getResource("/apresentacao/Cliente.fxml"));
			final Parent root = loader.load();
			controlador = loader.getController();

			final var scene = new Scene(root);
			new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
			stage.setScene(scene);
			if (!Funcionario.getFuncionario().getAdministrador()) {
				controlador.btnDesativarCliente.setDisable(true);
			}
			stage.show();
			stage.setOnCloseRequest(e -> {

				controlador.limpaTabela();

			});
		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}
	}

	public Cliente abreTelaClienteMenuAlterar(final TIPO_TELA tipo_tela) {

		try {
			tipo_telaa = tipo_tela;
			final var stage = new Stage();

			final var loader = new FXMLLoader();
			stage.initModality(Modality.APPLICATION_MODAL);
			loader.setLocation(getClass().getResource("/apresentacao/Cliente.fxml"));
			final Pane root = loader.load();
			controlador = loader.getController();

			final var scene = new Scene(root);
			new JMetro(scene, Main.style).setAutomaticallyColorPanes(true);
			stage.setScene(scene);

			controlador.btnInsereCliente.setDisable(true);
			controlador.btnInsereCliente.setVisible(false);
			controlador.btnAlterarCliente.setDisable(true);
			controlador.btnAlterarCliente.setVisible(false);
			controlador.btnDesativarCliente.setText("Selecionar");
			stage.setTitle("Consultar Cliente");
			stage.setOnCloseRequest(e -> {

				controlador.limpaTabela();

			});
			stage.showAndWait();

		} catch (final IOException e) {
			Alerta.alertaErro(e.getMessage());
		}

		return clienteAlterar;
	}

	@FXML
	private void btnAlterarCliente(final ActionEvent event) {
		final var cliente = tvCliente.getSelectionModel().getSelectedItem();
		tela.abreTelaClienteInsere(TIPO_TELA.ALTERA, cliente);
	}

	@FXML
	private void btnConsultaCliente(final ActionEvent event) {
		limpaTabela();
		final var negCliente = new NegCliente();
		try {
			final List<Cliente> cliente = negCliente.consultar(txtCliente.getText().trim());
			final var data = FXCollections.observableList(cliente);
			tvCliente.setItems(data);
			tcCodigo.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("Codigo"));
			tcAtivo.setCellValueFactory(new PropertyValueFactory<Cliente, Boolean>("Ativo"));
			tcCPF.setCellValueFactory(new PropertyValueFactory<Cliente, String>("CPF"));
			tcEndereco.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Endereco"));
			tcNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nome"));
			tcTelefone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Telefone"));

			tcCidade.setCellValueFactory(cidade -> new ReadOnlyStringWrapper(cidade.getValue().getCidade().getNome()));

			tcCodCidade.setCellValueFactory(
					codCidade -> new ReadOnlyIntegerWrapper(codCidade.getValue().getCidade().getCodigo()).asObject());

		} catch (final SQLException e) {

			Alerta.alertaErro(e.getMessage());
		}
	}

	@FXML
	void btnDesativarCliente(final ActionEvent event) {
		final var cliente = tvCliente.getSelectionModel().getSelectedItem();

		if (tipo_telaa.equals(TIPO_TELA.CONSULTA)) {

			clienteAlterar = cliente;
			btnDesativarCliente.getScene().getWindow().hide();

		} else {
			final var negCliente = new NegCliente();
			try {
				if (negCliente.excluir(cliente.getCodigo())) {
					tvCliente.getItems().remove(cliente);
					Alerta.alertaSucesso();
				}
			} catch (final SQLException e) {
				Alerta.alertaClienteEmCaixa();
			}
		}
	}

	@FXML
	void btnInsereCliente(final ActionEvent event) {
		tela.abreTelaClienteInsere(TIPO_TELA.INSERE, null);
	}

	private void limpaTabela() {
		tvCliente.getItems().clear();
	}
}
