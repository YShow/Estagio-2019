package negocio;

import acessoBD.MariaDB.AcessoBD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objeto.Produto;
import objeto.VendaProd;

public class NegProduto {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "insert into produto";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Produto produto) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_INSERT)) {
	    return false;
	}
    }

    public List<Produto> consultar(String metodo) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
	    var result = comando.executeQuery();
	    var lista = new ArrayList<Produto>();
	    while (result.next()) {
		var produto = new Produto();
		//
		//
		//
		lista.add(produto);
	    }
	    return lista;
	}
    }

    public boolean alterar(Produto produto) throws SQLException {
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
