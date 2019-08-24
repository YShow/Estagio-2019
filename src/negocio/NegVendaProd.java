package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.VendaProd;

public class NegVendaProd {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(VendaProd vendaProd) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_INSERT)) {
	    return false;
	}
    }

    public List<VendaProd> consultar(String metodo) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
	    var result = comando.executeQuery();
	    var lista = new ArrayList<VendaProd>();
	    while (result.next()) {
		var vendaProd = new VendaProd();
		//
		//
		//
		lista.add(vendaProd);
	    }
	    return lista;
	}
    }

    public boolean alterar(VendaProd vendaProd) throws SQLException {
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
