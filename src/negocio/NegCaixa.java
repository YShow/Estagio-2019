package negocio;

import acessoBD.MariaDB.AcessoBD;

import java.sql.SQLException;
import java.util.List;

import objeto.Caixa;

public class NegCaixa {
	private final AcessoBD conexao = new AcessoBD();
	private static final String SQL_INSERT = "INSERT INTO cantagalo.caixa\n"
			+ "(`data`, preco_total, saida, codigo_cliente)" + "VALUES(?, ?, ?, ?)";
	private static final String SQL_SEARCH = "SELECT codigo, data, preco_total, saida, codigo_cliente "
			+ "FROM cantagalo.caixa where data = ?";
	private static final String SQL_UPDATE = "UPDATE cantagalo.caixa "
			+ "SET data= ?, preco_total=?, saida= ?, codigo_cliente= ?" + "WHERE codigo= ?;";
	private static final String SQL_DELETE = "";

	public boolean inserir(Caixa caixa) throws SQLException {
		try (final var comando = conexao.getConexao().prepareStatement(SQL_INSERT)) {
			comando.setString(1, caixa.getData().toString());
			comando.setDouble(2, caixa.getPrecototal());
			comando.setDouble(3, caixa.getSaida());
			comando.setInt(4, caixa.getCliente().getCodigo());

			return comando.execute();
		}
	}

	public List<Caixa> consultar(String metodo) throws SQLException {
		try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
			comando.setString(1, metodo);
			var caixa = comando.executeQuery();

			return null;
		}
	}

	public boolean alterar(Caixa caixa) throws SQLException {
		try (var comando = conexao.getConexao().prepareStatement(SQL_UPDATE)) {

			return false;
		}
	}

	public boolean excluir(int id) throws SQLException {
		try (var comando = conexao.getConexao().prepareStatement(SQL_DELETE)) {
			return false;
		}
	}
}
