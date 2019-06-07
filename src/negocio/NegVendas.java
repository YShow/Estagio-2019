package negocio;

import static acessoBD.MariaDB.AcessoBD.getConexao;

import java.util.ArrayList;

import objeto.Vendas;

public class NegVendas {

    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Vendas vendas) throws Exception {
	var comando = getConexao().prepareStatement(SQL_INSERT);
	return false;
    }

    public ArrayList<Vendas> consultar(String metodo) throws Exception {
	var comando = getConexao().prepareStatement(SQL_SEARCH);
	return null;
    }

    public boolean alterar(Vendas vendas) throws Exception {
	var comando = getConexao().prepareStatement(SQL_UPDATE);
	return false;
    }

    public boolean excluir(int id) throws Exception {
	var comando = getConexao().prepareStatement(SQL_DELETE);
	return false;
    }
}
