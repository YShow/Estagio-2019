package negocio;

import static acessoBD.MariaDB.AcessoBD.getConexao;
import objeto.Vendas;

import static acessoBD.MariaDB.AcessoBD.getConexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NegVendas {

    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Vendas vendas) throws Exception {
	PreparedStatement comando = getConexao().prepareStatement(SQL_INSERT);
	return false;
    }

    public ArrayList<Vendas> consultar(String metodo) throws Exception {
	PreparedStatement comando = getConexao().prepareStatement(SQL_SEARCH);
	return null;
    }

    public boolean alterar(Vendas vendas) throws Exception {
	PreparedStatement comando = getConexao().prepareStatement(SQL_UPDATE);
	return false;
    }

    public boolean excluir(int id) throws Exception {
	PreparedStatement comando = getConexao().prepareStatement(SQL_DELETE);	
	return false;
    }
}
