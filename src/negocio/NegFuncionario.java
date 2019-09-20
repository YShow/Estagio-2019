package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.Funcionario;
import utilidade.Senha;

public final class NegFuncionario {
	private final AcessoBD conexao = new AcessoBD();
	private static final String SQL_INSERT = "insert into funcionario(nome,funcao,administrador,senhahash,salt,usuario,ativo)"
			+ " values(?,?,?,?,?,?,?)";
	private static final String SQL_SEARCH = "select codigo,nome,funcao,administrador,usuario from funcionario"
			+ "  where nome LIKE ?  and ativo = true";
	private static final String SQL_UPDATE = "update funcionario set nome = ?, funcao = ?, administrador = ?,"
			+ "senhahash =COALESCE(?,senhahash), salt =COALESCE(?,salt),usuario = ?,ativo = ? where codigo = ?";
	private static final String SQL_DELETE = "update funcionario set ativo = ?" + "WHERE codigo=? ;";

	public final boolean inserir(final Funcionario funcionario) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		con.setAutoCommit(false);
		final var comando = con.prepareStatement(SQL_INSERT);

		try (con; comando;) {
			final var salt = Senha.geraSalt();
			comando.setString(1, funcionario.getNome());
			comando.setString(2, funcionario.getFuncao());
			comando.setBoolean(3, funcionario.getAdministrador());
			comando.setString(4, Senha.criaSenha(funcionario.getSenha(), salt));
			comando.setString(5, salt);
			comando.setString(6, funcionario.getUsuario());
			comando.setBoolean(7, funcionario.isAtivo());
			final var inseriu = comando.executeUpdate() >= 1;
			con.commit();
			System.out.println(
					"Insercao de funcionario demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return inseriu;
		}
	}

	public final List<Funcionario> consultar(final String metodo) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setReadOnly(true);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_SEARCH);
		try (con; comando;) {
			comando.setString(1, '%' + metodo + '%');
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

			System.out.println(
					"Consulta de funcionario demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");

			return lista;
		}
	}

	public final boolean alterar(final Funcionario funcionario) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_UPDATE);
		try (con; comando;) {

			comando.setString(1, funcionario.getNome());
			comando.setString(2, funcionario.getFuncao());
			comando.setBoolean(3, funcionario.getAdministrador());
			if (funcionario.getSenha().isBlank()) {

				comando.setString(4, null);
				comando.setString(5, null);
			} else {
				final var salt = Senha.geraSalt();
				comando.setString(4, Senha.criaSenha(funcionario.getSenha(), salt));
				comando.setString(5, salt);
			}
			comando.setString(6, funcionario.getUsuario());
			comando.setBoolean(7, funcionario.isAtivo());
			comando.setInt(8, funcionario.getCodigo());

			final var alterou = comando.executeUpdate() >= 1;

			con.commit();
			System.out.println(
					"Alteração de funcionario demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
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

			comando.setBoolean(1, false);
			comando.setInt(2, id);
			final var excluiu = comando.executeUpdate() >= 1;
			con.commit();
			System.out.println(
					"Exclusao de funcionario demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return excluiu;
		}
	}

}
