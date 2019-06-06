package negocio;

import acessoBD.MariaDB.AcessoBD;
import objeto.Vendas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NegVendas {

    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Vendas vendas) throws Exception {
	return false;
    }

    public ArrayList<Vendas> consultar(String metodo) throws Exception {
	return null;
    }

    public boolean alterar(Vendas vendas) throws Exception {
	return false;
    }

    public boolean excluir(int id) throws Exception {
	return false;
    }
}
