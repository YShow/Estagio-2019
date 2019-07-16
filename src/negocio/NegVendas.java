package negocio;

import acessoBD.MariaDB.AcessoBD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objeto.Vendas;

public class NegVendas {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Vendas vendas) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_INSERT)) {
	    return false;

	}
    }

    public List<Vendas> consultar(String metodo) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
	    var result = comando.executeQuery();
	    var lista = new ArrayList<Vendas>();
	    while (result.next()) {
		var venda = new Vendas();
		//
		//
		//
		lista.add(venda);
	    }
	    return lista;
	}
    }

    public boolean alterar(Vendas vendas) throws SQLException {
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
