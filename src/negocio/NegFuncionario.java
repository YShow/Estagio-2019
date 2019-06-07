package negocio;

import static acessoBD.MariaDB.AcessoBD.getConexao;

import java.util.ArrayList;

import objeto.Funcionario;

public class NegFuncionario {
    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(Funcionario funcionario) throws Exception {
	var comando = getConexao().prepareStatement(SQL_INSERT);
	return false;
    }

    public ArrayList<Funcionario> consultar(String metodo) throws Exception {
	var comando = getConexao().prepareStatement(SQL_SEARCH);
	return null;
    }

    public boolean alterar(Funcionario funcionario) throws Exception {
	var comando = getConexao().prepareStatement(SQL_UPDATE);
	return false;
    }

    public boolean excluir(int id) throws Exception {
	var comando = getConexao().prepareStatement(SQL_DELETE);
	return false;
    }

}
