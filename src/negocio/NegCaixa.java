package negocio;

import static acessoBD.MariaDB.AcessoBD.getConexao;
import objeto.Caixa;

import java.sql.ResultSet;
import java.util.ArrayList;

public class NegCaixa {
    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Caixa caixa) throws Exception {
	var comando = getConexao().prepareStatement(SQL_INSERT);

	return false;
    }

    public ArrayList<Caixa> consultar(String metodo) throws Exception {
	var comando = getConexao().prepareStatement(SQL_SEARCH);
	return null;
    }

    public boolean alterar(Caixa caixa) throws Exception {
	var comando = getConexao().prepareStatement(SQL_UPDATE);
	return false;
    }

    public boolean excluir(int id) throws Exception {
	var comando = getConexao().prepareStatement(SQL_DELETE);
	return false;
    }
}
