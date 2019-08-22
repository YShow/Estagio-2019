package negocio;

import acessoBD.MariaDB.AcessoBD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objeto.Funcionario;
import objeto.VendaProd;

public class NegFuncionario {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "insert into funcionario(nome,funcao,administrador,senha)"
	    + " values(?,?,?,?)";
    private static final String SQL_SEARCH = "select codigo,nome,funcao,administrador,senha from funcionario"
    	+ "  where nome LIKE ? ";
    private static final String SQL_UPDATE = "update funcionario set nome = ?, funcao = ?, administrador = ?,"
    	+ "senha = ? where codigo = ?";
    private static final String SQL_DELETE = "";

    public int inserir(Funcionario funcionario) throws SQLException {
	
	try (var comando = conexao.getConexao().prepareStatement(SQL_INSERT)) {
	    comando.setString(1, funcionario.getNome());
	    comando.setString(2, funcionario.getFuncao());
	    comando.setBoolean(3,funcionario.getAdministrador());
	    comando.setString(4, funcionario.getSenha());	    
	    return comando.executeUpdate();	    
	}
    }

    public List<Funcionario> consultar(String metodo) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
	    comando.setString(1, '%' + metodo + '%');
	    var result = comando.executeQuery();
	    var lista = new ArrayList<Funcionario>();
	  
	    while (result.next()) {
		var funcionario = new Funcionario();
		funcionario.setCodigo(result.getInt("codigo"));
		funcionario.setNome(result.getString("nome"));
		funcionario.setFuncao(result.getString("funcao"));
		funcionario.setAdministrador(result.getBoolean("administrador"));
		funcionario.setSenha(result.getString("senha"));		
		lista.add(funcionario);
	    }
	    
	    return lista;
	}
    }

    public int alterar(Funcionario funcionario) throws SQLException {
	
	try (var comando = conexao.getConexao().prepareStatement(SQL_UPDATE)) {
	    comando.setString(1, funcionario.getNome());
	    comando.setString(2, funcionario.getFuncao());
	    comando.setBoolean(3,funcionario.getAdministrador());
	    comando.setString(4, funcionario.getSenha());
	    comando.setInt(5,funcionario.getCodigo());
	    return comando.executeUpdate();
	}
    }

    public boolean excluir(int id) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_DELETE)) {
	    return false;
	}
    }

}
