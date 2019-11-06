package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.resource.transaction.spi.TransactionStatus;

import acessoBD.MariaDB.AcessoBD;
import acessoBD.MariaDB.HibernateUtil;
import objeto.Funcionario;

public final class NegFuncionario {
	private final AcessoBD conexao = new AcessoBD();
	private static final Logger logger = Logger.getLogger(NegFuncionario.class.getName());
	private static final String SQL_SEARCH = "SELECT codigo,nome,funcao,administrador,usuario FROM funcionario WHERE MATCH(nome,usuario) AGAINST(? IN BOOLEAN MODE) and ativo = true";
	private static final String SQL_UPDATE = "update funcionario set nome = ?, funcao = ?, administrador = ?,"
			+ "senhahash =COALESCE(?,senhahash), salt =COALESCE(?,salt),usuario = ?,ativo = ? where codigo = ?";
	private static final String SQL_DELETE = "update funcionario set ativo = ? WHERE codigo=? ;";

	public final boolean inserir(final Funcionario funcionario) {
		final var comeco = Instant.now();
		try (final var session = HibernateUtil.getSessionFactory().openSession()) {
			final var transaction = session.beginTransaction();
			session.save(funcionario);
			transaction.commit();
			return session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
		} finally {
			logger.log(Level.INFO,
					() -> "Inserir funcionario demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

	// TODO fazer o consultar com hibernate
	public final List<Funcionario> consultar(final String metodo) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_SEARCH);
		try (con; comando;) {
			con.setAutoCommit(false);
			con.setReadOnly(true);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			comando.setString(1, metodo + '*');
			final var result = comando.executeQuery();

			final var lista = new ArrayList<Funcionario>();

			while (result.next()) {
				final var funcionario = new Funcionario();
				funcionario.setCodigo(result.getInt("codigo"));
				funcionario.setNome(result.getString("nome"));
				funcionario.setFuncao(result.getString("funcao"));
				funcionario.setAdministrador(result.getBoolean("administrador"));
				funcionario.setUsuario(result.getString("usuario"));

				lista.add(funcionario);
			}

			return lista;
		} finally {
			logger.log(Level.INFO, () -> "Consultar funcionario demorou: "
					+ Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

	public final boolean alterar(final Funcionario funcionario) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_UPDATE);
		try (con; comando;) {
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			comando.setString(1, funcionario.getNome());
			comando.setString(2, funcionario.getFuncao());
			comando.setBoolean(3, funcionario.isAdministrador());
			if (funcionario.getSenha().isBlank()) {

				comando.setString(4, null);
				comando.setString(5, null);
			} else {

				comando.setString(4, funcionario.getSenha());
				comando.setString(5, funcionario.getSalt());
			}
			comando.setString(6, funcionario.getUsuario());
			comando.setBoolean(7, funcionario.isAtivo());
			comando.setInt(8, funcionario.getCodigo());

			final var alterou = comando.executeUpdate() >= 1;

			con.commit();
			return alterou;
		} finally {
			logger.log(Level.INFO,
					() -> "Alterar funcionario demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
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
					() -> "Excluir funcionario demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

}
