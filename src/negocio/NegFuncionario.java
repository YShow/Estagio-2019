package negocio;


import objeto.Funcionario;

import static acessoBD.MariaDB.AcessoBD.getConexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NegFuncionario {
    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Funcionario funcionario) throws Exception {
	PreparedStatement comando = getConexao().prepareStatement(SQL_INSERT);
	return false;
    }

    public ArrayList<Funcionario> consultar(String metodo) throws Exception {
	PreparedStatement comando = getConexao().prepareStatement(SQL_SEARCH);
	return null;
    }

    public boolean alterar(Funcionario funcionario) throws Exception {
	PreparedStatement comando = getConexao().prepareStatement(SQL_UPDATE);
	return false;
    }

    public boolean excluir(int id) throws Exception {
	PreparedStatement comando = getConexao().prepareStatement(SQL_DELETE);	
	return false;
    }

}
