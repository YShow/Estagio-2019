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
import objeto.Cliente;

public final class NegCliente {
	private final AcessoBD conexao = new AcessoBD();
	private static final Logger logger = Logger.getLogger(NegCliente.class.getName());
	private static final String SQL_SEARCH = "SELECT c.codigo, c.nome, c.CPF, c.endereco, c.telefone, c.ativo, c.id_cidade, ci.nome\n"
			+ "FROM cantagalo.cliente c JOIN cidade ci ON c.id_cidade = ci.codigo\n"
			+ "WHERE MATCH(c.nome) AGAINST(:nome IN BOOLEAN MODE)";
	private static final String SQL_UPDATE = "update cliente set nome = ?, CPF = ?, endereco = ?,"
			+ "telefone = ?, ativo = ?, id_cidade = ? where codigo = ?;";
	private static final String SQL_DELETE = "UPDATE cantagalo.cliente SET ativo=? WHERE codigo=?;";

	public final boolean inserir(final Cliente cliente) {
		final var comeco = Instant.now();
		try (final var session = HibernateUtil.getSessionFactory().openSession()) {
			final var transaction = session.beginTransaction();
			session.save(cliente);
			transaction.commit();
			return session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
		} finally {
			logger.log(Level.INFO,
					() -> "Inserir de cliente demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}

	}

	public final List<Cliente> consultar(final String metodo) {
		final var comeco = Instant.now();
		try (final var session = HibernateUtil.getSessionFactory().openSession()) {
			final var query = session.createNativeQuery(SQL_SEARCH, Cliente.class);
			query.setParameter("nome", metodo + '*');
			return query.getResultList();
		} finally {
			logger.log(Level.INFO,
					() -> "Consulta de cliente demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}

	}

	public final boolean alterar(final Cliente cliente) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_UPDATE);
		try (con; comando) {
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			comando.setString(1, cliente.getNome());
			comando.setString(2, cliente.getCpf());
			comando.setString(3, cliente.getEndereco());
			comando.setString(4, cliente.getTelefone());
			comando.setBoolean(5, cliente.isAtivo());
			comando.setInt(6, cliente.getCidade().getCodigo());
			comando.setInt(7, cliente.getCodigo());
			final var alterou = comando.executeUpdate() >= 1;
			con.commit();
			return alterou;
		} finally {
			logger.log(Level.INFO,
					() -> "Alterar cliente demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

	public final boolean excluir(final int id) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_DELETE);
		try (con; comando;) {
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			comando.setBoolean(1, false);
			comando.setInt(2, id);
			final var excluiu = comando.executeUpdate() >= 1;
			con.commit();
			return excluiu;
		} finally {
			logger.log(Level.INFO,
					() -> "Excluir de cliente demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}

	}

}
