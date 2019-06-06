package negocio;

import acessoBD.MariaDB.AcessoBD;
import objeto.Cidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NegCidade {
    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Cidade cidade) throws Exception {
	return false;
    }

    public ArrayList<Cidade> consultar(String metodo) throws Exception {
	return null;
    }

    public boolean alterar(Cidade cidade) throws Exception {
	return false;
    }

    public boolean excluir(int id) throws Exception {
	return false;
    }
}
