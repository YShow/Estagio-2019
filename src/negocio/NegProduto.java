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
import objeto.Produto;

public final class NegProduto {
	private final AcessoBD conexao = new AcessoBD();
	private static final Logger logger = Logger.getLogger(NegProduto.class.getName());
	private static final String SQL_INSERT = "INSERT INTO cantagalo.produto\n" + "(ativo, preco, quantidade, nome)\n"
			+ "VALUES(?, ?, ?, ?);\n";
	private static final String SQL_SEARCH = "SELECT codigo, ativo, preco, quantidade, nome FROM produto WHERE MATCH(nome) AGAINST(? IN BOOLEAN MODE)";
	private static final String SQL_UPDATE = "UPDATE cantagalo.produto\n"
			+ "SET ativo=?, preco=?, quantidade=?, nome=?\n" + "WHERE codigo=? ;";
	private static final String SQL_DELETE = "UPDATE cantagalo.produto SET ativo=? WHERE codigo=?;";

	public final boolean inserir(final Produto produto) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_INSERT);
		try (con; comando;) {
			comando.setBoolean(1, produto.getAtivo());
			comando.setDouble(2, produto.getPreco());
			comando.setInt(3, produto.getQuantidade());
			comando.setString(4, produto.getNome());
			final var inseriu = comando.executeUpdate() >= 1;
			con.commit();
			return inseriu;
		} finally {
			logger.log(Level.INFO,
					() -> "Inserir produto demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}

	}

	public final List<Produto> consultar(final String metodo) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		con.setAutoCommit(false);
		con.setReadOnly(true);
		final var comando = con.prepareStatement(SQL_SEARCH);
		try (con; comando;) {
			comando.setString(1, metodo + '*');
			final var result = comando.executeQuery();
			final var lista = new ArrayList<Produto>();
			while (result.next()) {
				final var produto = new Produto();
				produto.setAtivo(result.getBoolean("ativo"));
				produto.setNome(result.getString("nome"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQuantidade(result.getInt("quantidade"));
				produto.setCodigo(result.getInt("codigo"));

				lista.add(produto);
			}
			return lista;
		} finally {
			logger.log(Level.INFO,
					() -> "Consultar produto demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

	public final boolean alterar(final Produto produto) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_UPDATE);
		try (con; comando;) {
			comando.setBoolean(1, produto.getAtivo());
			comando.setDouble(2, produto.getPreco());
			comando.setInt(3, produto.getQuantidade());
			comando.setString(4, produto.getNome());
			comando.setInt(5, produto.getCodigo());
			final var alterou = comando.executeUpdate() >= 1;
			con.commit();
			return alterou;
		} finally {
			logger.log(Level.INFO,
					() -> "Alterar produto demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
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
			return excluiu;
		} finally {
			logger.log(Level.INFO,
					() -> "Excluir produto demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}
}
