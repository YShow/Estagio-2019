package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import acessoBD.MariaDB.AcessoBD;
import objeto.Cidade;

public final class NegCidade {
	private final AcessoBD conexao = new AcessoBD();
	private static final Logger logger = Logger.getLogger(NegCidade.class.getName());
	private static final String SQL_INSERT = "INSERT INTO cidade(nome,estado) values(?,?)";
	private static final String SQL_SEARCH = "SELECT codigo,nome,estado FROM cidade WHERE MATCH(nome) AGAINST(? IN BOOLEAN MODE)";
	private static final String SQL_UPDATE = "update cidade set nome = ?, estado = ? where codigo = ?";
	private static final String SQL_DELETE = "DELETE FROM cantagalo.cidade\n" + "WHERE codigo = ? ;";

	public final boolean inserir(final Cidade cidade) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_INSERT);
		try (con; comando;) {

			comando.setString(1, cidade.getNome());
			comando.setString(2, cidade.getEstado());
			final var inseriu = comando.executeUpdate() >= 1;
			con.commit();
			return inseriu;
		} finally {
			logger.log(Level.INFO,
					() -> "Inserir cidade demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

	public final List<Cidade> consultar(final String metodo) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		con.setReadOnly(true);
		final var comando = con.prepareStatement(SQL_SEARCH);
		try (con; comando;) {

			comando.setString(1, metodo + '*');
			final var result = comando.executeQuery();
			final var lista = new ArrayList<Cidade>();
			while (result.next()) {
				final var cidade = new Cidade();
				cidade.setCodigo(result.getInt("codigo"));
				cidade.setNome(result.getString("nome"));
				cidade.setEstado(result.getString("estado"));
				lista.add(cidade);
			}
			return lista;
		} finally {
			logger.log(Level.INFO,
					() -> "Consultar cidade demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

	public final boolean alterar(final Cidade cidade) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_UPDATE);
		try (con; comando;) {

			comando.setString(1, cidade.getNome());
			comando.setString(2, cidade.getEstado());
			comando.setInt(3, cidade.getCodigo());
			final var alterou = comando.executeUpdate() >= 1;
			con.commit();
			return alterou;
		} finally {
			logger.log(Level.INFO,
					() -> "Alterar cidade demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

	public final boolean excluir(final int id) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_DELETE);
		try (con; comando) {

			comando.setInt(1, id);
			final var excluiu = comando.executeUpdate() >= 1;
			con.commit();
			return excluiu;
		} finally {
			logger.log(Level.INFO,
					() -> "Excluir cidade demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}
}
