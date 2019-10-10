package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.Cidade;
import objeto.Cliente;

public final class NegCliente {
	private final AcessoBD conexao = new AcessoBD();
	private static final String SQL_INSERT = "insert into cliente(nome,CPF,endereco,telefone,ativo,id_cidade)"
			+ " values(?,?,?,?,?,?)";
	private static final String SQL_SEARCH = "SELECT c.codigo, c.nome, c.CPF, c.endereco, c.telefone, c.ativo, c.id_cidade, ci.nome\n"
			+ "FROM cantagalo.cliente c JOIN cidade ci ON c.id_cidade = ci.codigo\n"
			+ "WHERE MATCH(c.nome) AGAINST(? IN BOOLEAN MODE)";
	private static final String SQL_UPDATE = "update cliente set nome = ?, CPF = ?, endereco = ?,"
			+ "telefone = ?, ativo = ?, id_cidade = ? where codigo = ?;";
	private static final String SQL_DELETE = "DELETE FROM cantagalo.cliente\n" + " WHERE codigo=?;";

	public final boolean inserir(final Cliente cliente) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_INSERT);
		try (con; comando;) {
			comando.setString(1, cliente.getNome());
			comando.setString(2, cliente.getCPF());
			comando.setString(3, cliente.getEndereco());
			comando.setString(4, cliente.getTelefone());
			comando.setBoolean(5, cliente.getAtivo());
			comando.setInt(6, cliente.getCidade().getCodigo());
			final var inseriu = comando.executeUpdate() >= 1;

			con.commit();
			System.out.println(
					"Insercao de Cliente demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return inseriu;
		}
	}

	public final List<Cliente> consultar(final String metodo) throws SQLException {
		final var comeco = Instant.now();

		final var con = conexao.getConexao();

		final var comando = con.prepareStatement(SQL_SEARCH);
		try (con; comando;) {
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			con.setReadOnly(true);

			comando.setString(1, metodo + '*');
			final List<Cliente> lista = new ArrayList<>();
			final var result = comando.executeQuery();

			while (result.next()) {
				final var cliente = new Cliente();
				final var cidade = new Cidade();
				cliente.setCodigo(result.getInt("c.codigo"));
				cliente.setNome(result.getString("c.nome"));
				cliente.setCPF(result.getString("c.CPF"));
				cliente.setEndereco(result.getString("c.endereco"));
				cliente.setTelefone(result.getString("c.telefone"));
				cliente.setAtivo(result.getBoolean("c.ativo"));

				cidade.setNome(result.getString("ci.nome"));
				cidade.setCodigo(result.getInt("c.id_cidade"));

				cliente.setCidade(cidade);
				lista.add(cliente);
			}
			System.out.println(
					"Consulta de cliente demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return lista;
		}

	}

	public final boolean alterar(final Cliente cliente) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_UPDATE);
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		try (con; comando) {

			comando.setString(1, cliente.getNome());
			comando.setString(2, cliente.getCPF());
			comando.setString(3, cliente.getEndereco());
			comando.setString(4, cliente.getTelefone());
			comando.setBoolean(5, cliente.getAtivo());
			comando.setInt(6, cliente.getCidade().getCodigo());
			comando.setInt(7, cliente.getCodigo());
			final var alterou = comando.executeUpdate() >= 1;
			con.commit();
			System.out.println(
					"Alterar de cliente demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return alterou;
		}
	}

	public final boolean excluir(final int id) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_DELETE);
		try (con; comando;) {
			comando.setInt(1, id);
			final var excluiu = comando.executeUpdate() >= 1;
			con.commit();
			System.out.println(
					"Excluir de cliente demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return excluiu;
		}
	}
}
