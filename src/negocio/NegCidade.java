package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.resource.transaction.spi.TransactionStatus;

import acessoBD.MariaDB.AcessoBD;
import acessoBD.MariaDB.HibernateUtil;
import objeto.Cidade;

public final class NegCidade {
	private final AcessoBD conexao = new AcessoBD();
	private static final Logger logger = Logger.getLogger(NegCidade.class.getName());
	private static final String SQL_SEARCH = "SELECT codigo,nome,estado FROM cidade WHERE MATCH(nome) AGAINST(:nome IN BOOLEAN MODE)";
	private static final String SQL_UPDATE = "update cidade set nome = ?, estado = ? where codigo = ?";
	private static final String SQL_DELETE = "DELETE FROM cantagalo.cidade\n" + "WHERE codigo = ? ;";

	public final boolean inserir(final Cidade cidade) {
		final var comeco = Instant.now();
		try (final var session = HibernateUtil.getSessionFactory().openSession()) {
			final var transaction = session.beginTransaction();
			session.save(cidade);
			transaction.commit();
			return session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
		} finally {
			logger.log(Level.INFO,
					() -> "Inserir cidade demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

	public final List<Cidade> consultar(final String metodo) {
		final var comeco = Instant.now();
		try (final var session = HibernateUtil.getSessionFactory().openSession()) {
			final var query = session.createNativeQuery(SQL_SEARCH, Cidade.class);
			query.setParameter("nome", metodo + '*');
			return query.getResultList();
		} finally {
			logger.log(Level.INFO,
					() -> "Consultar cidade demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

	public final boolean alterar(final Cidade cidade) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_UPDATE);
		try (con; comando;) {
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
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
		final var comando = con.prepareStatement(SQL_DELETE);
		try (con; comando) {
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
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
