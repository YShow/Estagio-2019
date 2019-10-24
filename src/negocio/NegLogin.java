package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

import acessoBD.MariaDB.AcessoBD;
import objeto.Funcionario;
import utilidade.Senha;

public final class NegLogin {
	private final AcessoBD conexao = new AcessoBD();
	private static final Logger logger = Logger.getLogger(NegLogin.class.getName());
	private static final String SQL_SEARCH = "SELECT codigo,usuario,salt, senhahash, administrador from funcionario WHERE  usuario = ?;";

	private static final String SQL_VERIFICA_SE_TEM_USUARIO = "SELECT COUNT(administrador) as qtd_adm_ativo from funcionario\n"
			+ " WHERE administrador = true and ativo = true";

	public final boolean verificaLogin(final Funcionario funcionario) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_SEARCH);
		try (con; comando;) {
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			con.setAutoCommit(false);
			con.setReadOnly(true);
			var existeUsuario = false;
			comando.setString(1, funcionario.getUsuario());
			final var resultado = comando.executeQuery();
			if (resultado.next()) {

				final var funcionarioPadrao = new Funcionario();
				if (Senha.senhaCorreta(funcionario.getSenha(), resultado.getString("senhahash"),
						resultado.getString("salt"))) {

					funcionarioPadrao.setAdministrador(resultado.getBoolean("administrador"));
					funcionarioPadrao.setCodigo(resultado.getInt("codigo"));
					Funcionario.setFuncionario(funcionarioPadrao);

					existeUsuario = true;
				}
			}
			return existeUsuario;
		} finally {
			System.gc();
			logger.log(Level.INFO,
					() -> "Login demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

	public final boolean ePrimeiroLogin() throws SQLException {
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_VERIFICA_SE_TEM_USUARIO);
		try (con; comando;) {
			con.setReadOnly(true);
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			final var result = comando.executeQuery();

			if (result.next()) {
				return result.getInt("qtd_adm_ativo") >= 1;
			} else {
				return false;
			}
		}
	}
}
