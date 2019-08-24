package negocio;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
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

    public int inserir(Caixa caixa) throws SQLException {
	try (final var comando = conexao.getConexao().prepareStatement(SQL_INSERT)) {
	    comando.setObject(1, caixa.getData());
	    comando.setDouble(2, caixa.getPrecototal());
	    comando.setDouble(3, caixa.getSaida());
	    comando.setInt(4, caixa.getCliente());

	    return comando.executeUpdate();
	}
    }

    public List<Caixa> consultar(String metodo) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
	    comando.setString(1, metodo);

	    var result = comando.executeQuery();
	    var lista = new ArrayList<Caixa>();
	    while (result.next()) {
		var caixa = new Caixa();
		caixa.setCodigo(result.getInt("codigo"));
		caixa.setData(result.getObject("data", LocalDate.class));
		caixa.setPrecototal(result.getDouble("preco_total"));
		caixa.setSaida(result.getDouble("saida"));
		caixa.setCliente(result.getInt("codigo_cliente"));
		lista.add(caixa);
	    }
	    return lista;
	}
    }

    public int alterar(Caixa caixa) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_UPDATE)) {
	    comando.setObject(1, caixa.getData());
	    comando.setDouble(2, caixa.getPrecototal());
	    comando.setDouble(3, caixa.getSaida());
	    comando.setDouble(4, caixa.getCliente());
	    comando.setDouble(5, caixa.getCodigo());
	    return comando.executeUpdate();
	}
    }

    public int excluir(int id) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_DELETE)) {
	    comando.setInt(1, id);
	    return comando.executeUpdate();
	}
    }
}
