package negocio;

import acessoBD.MariaDB.AcessoBD;
import objeto.Funcionario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NegFuncionario {
    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Funcionario funcionario) throws Exception {
	return false;
    }

    public ArrayList<Funcionario> consultar(String metodo) throws Exception {
	return null;
    }

    public boolean alterar(Funcionario funcionario) throws Exception {
	return false;
    }

    public boolean excluir(int id) throws Exception {
	return false;
    }

}
