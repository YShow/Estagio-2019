package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.collections.impl.list.mutable.FastList;

import acessoBD.MariaDB.AcessoBD;
import objeto.Caixa;

public final class NegCaixa {
	private final AcessoBD conexao = new AcessoBD();
	private static final Logger logger = Logger.getLogger(NegCaixa.class.getName());
	private static final String SQL_INSERT = "INSERT INTO cantagalo.caixa\n"
			+ "(`data`, preco_total, saida, codigo_funcionario,ativo)" + "VALUES(?, ?, ?, ?,true)";
	private static final String SQL_SEARCH = "SELECT codigo, data, preco_total, saida, codigo_funcionario,ativo "
			+ "FROM cantagalo.caixa where data = ?";

	public final boolean inserir(final Caixa caixa) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_INSERT);
		try (con; comando;) {
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			con.setAutoCommit(false);
			comando.setObject(1, caixa.getData());
			comando.setDouble(2, caixa.getPrecototal());
			comando.setDouble(3, caixa.getSaida());
			comando.setInt(4, caixa.getFuncionario());
			final var inseriu = comando.executeUpdate() >= 1;
			con.commit();
			return inseriu;
		} finally {
			logger.log(Level.INFO,
					() -> "Inserir caixa demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}

	}

	public final List<Caixa> consultar(final LocalDate data) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		final var comando = con.prepareStatement(SQL_SEARCH);
		try (con; comando;) {
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			con.setReadOnly(true);
			comando.setObject(1, data);

			final var result = comando.executeQuery();
			final var lista = new FastList<Caixa>();

			while (result.next()) {
				final var caixa = new Caixa();
				caixa.setCodigo(result.getInt("codigo"));
				caixa.setData(result.getObject("data", LocalDate.class));
				caixa.setPrecototal(result.getDouble("preco_total"));
				caixa.setSaida(result.getDouble("saida"));
				caixa.setFuncionario(result.getInt("codigo_funcionario"));
				caixa.setAtivo(result.getBoolean("ativo"));
				lista.add(caixa);
			}
			return lista;
		} finally {
			logger.log(Level.INFO,
					() -> "Consultar caixa demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

}
