package negocio;

import acessoBD.MariaDB.AcessoBD;
import objeto.Caixa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NegCaixa {
    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Caixa caixa) throws Exception {
	return false;
    }

    public ArrayList<Caixa> consultar(String metodo) throws Exception {
	return null;
    }

    public boolean alterar(Caixa caixa) throws Exception {
	return false;
    }

    public boolean excluir(int id) throws Exception {
	return false;
    }
}
