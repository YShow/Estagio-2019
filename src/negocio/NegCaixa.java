package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.Caixa;

public final class NegCaixa {
	private final AcessoBD conexao = new AcessoBD();
	private static final String SQL_INSERT = "INSERT INTO cantagalo.caixa\n"
			+ "(`data`, preco_total, saida, codigo_cliente)" + "VALUES(?, ?, ?, ?)";
	private static final String SQL_SEARCH = "SELECT codigo, data, preco_total, saida, codigo_cliente,ativo "
			+ "FROM cantagalo.caixa where data = ?";
	private static final String SQL_UPDATE = "UPDATE cantagalo.caixa "
			+ "SET data= ?, preco_total=?, saida= ?, codigo_cliente= ?" + "WHERE codigo= ?;";
	private static final String SQL_DELETE = "";

	public final boolean inserir(final Caixa caixa) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_INSERT);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		con.setAutoCommit(false);
		try (con; comando;) {
			comando.setObject(1, caixa.getData());
			comando.setDouble(2, caixa.getPrecototal());
			comando.setDouble(3, caixa.getSaida());
			comando.setInt(4, caixa.getCliente());
			final var inseriu = comando.executeUpdate() >= 1;
			con.commit();
			System.out
					.println("Insercao de caixa demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return inseriu;
		}
	}

	public final List<Caixa> consultar(final LocalDate data) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_SEARCH);
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		con.setReadOnly(true);
		try (con; comando;) {
			comando.setObject(1, data);

			final var result = comando.executeQuery();
			final List<Caixa> lista = new ArrayList<>();
			while (result.next()) {
				final var caixa = new Caixa();
				caixa.setCodigo(result.getInt("codigo"));
				caixa.setData(result.getObject("data", LocalDate.class));
				caixa.setPrecototal(result.getDouble("preco_total"));
				caixa.setSaida(result.getDouble("saida"));
				caixa.setCliente(result.getInt("codigo_cliente"));
				caixa.setAtivo(result.getBoolean("ativo"));
				lista.add(caixa);
			}
			System.out
					.println("Consulta de caixa demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return lista;
		}
	}

	public final boolean alterar(final Caixa caixa) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_UPDATE);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		con.setAutoCommit(false);
		try (con; comando;) {
			comando.setObject(1, caixa.getData());
			comando.setDouble(2, caixa.getPrecototal());
			comando.setDouble(3, caixa.getSaida());
			comando.setDouble(4, caixa.getCliente());
			comando.setDouble(5, caixa.getCodigo());
			final var alterou = comando.executeUpdate() >= 1;
			con.commit();

			System.out
					.println("Alterar de caixa demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return alterou;
		}
	}

	public final boolean excluir(final int id) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_DELETE);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		con.setAutoCommit(false);
		try (con; comando;) {
			comando.setInt(1, id);
			final var excluiu = comando.executeUpdate() >= 1;
			con.commit();
			System.out
					.println("Alterar de caixa demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return excluiu;
		}
	}
}
