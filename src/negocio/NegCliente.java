package negocio;

import acessoBD.MariaDB.AcessoBD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objeto.Cliente;

public class NegCliente {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Cliente cliente) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_INSERT)) {
	    return false;
	}
    }

    public List<Cliente> consultar(String metodo) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {

	    return null;
	}
    }

    public boolean alterar(Cliente cliente) throws SQLException {
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
