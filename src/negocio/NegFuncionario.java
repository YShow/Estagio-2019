package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.Funcionario;

public class NegFuncionario {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "insert into funcionario(nome,funcao,administrador,senha)"
	    + " values(?,?,?,?)";
    private static final String SQL_SEARCH = "select codigo,nome,funcao,administrador,senha from funcionario"
	    + "  where nome LIKE ? ";
    private static final String SQL_UPDATE = "update funcionario set nome = ?, funcao = ?, administrador = ?,"
	    + "senha = ? where codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM cantagalo.funcionario\n" + "WHERE codigo=? ;";

    public boolean inserir(final Funcionario funcionario) throws SQLException {

	try (final var con = conexao.getConexao()) {
	    final var startTime = System.currentTimeMillis();
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    con.setAutoCommit(false);
	    var comando = con.prepareStatement(SQL_INSERT);
	  /*
	    comando.setString(1, funcionario.getNome());
	    comando.setString(2, funcionario.getFuncao());
	    comando.setBoolean(3, funcionario.getAdministrador());
	    comando.setString(4, funcionario.getSenha());
	    */
	    var a = 0;
	    while(a < 1000000)
	    {
	    comando.setString(1, "123");
	    comando.setString(2, "123");
	    comando.setBoolean(3, true);
	    comando.setString(4, "123");
	    comando.addBatch();
	    a++;
	}
	   final var inseriu = comando.executeBatch();
	    con.commit();
	    final var endTime = System.currentTimeMillis();
	    System.out.println("tempo total desde inserir funcionario " + (endTime-startTime) + "ms"); 
	    return inseriu.length >= 1;
	}
    }

    public List<Funcionario> consultar(final String metodo) throws SQLException {
	try (final var con = conexao.getConexao()) {
	    final var startTime = System.currentTimeMillis();
	    //con.setAutoCommit(false);
	    //con.setReadOnly(true);
	    //con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_SEARCH);
	    comando.setString(1, '%' + metodo + '%');
	    var result = comando.executeQuery();
	   // con.commit();
	    
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
	    final var endTime = System.currentTimeMillis();
	    System.out.println("tempo total desde select funcionario " + (endTime-startTime) + "ms"); 
	    System.out.println("tamanho da lista de pessoas: " + lista.size());
	    return lista;
	}
    }

    public boolean alterar(final Funcionario funcionario) throws SQLException {

	try (final var con = conexao.getConexao()) {
	    final var startTime = System.currentTimeMillis();
	    con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_UPDATE);
	    comando.setString(1, funcionario.getNome());
	    comando.setString(2, funcionario.getFuncao());
	    comando.setBoolean(3, funcionario.getAdministrador());
	    comando.setString(4, funcionario.getSenha());
	    comando.setInt(5, funcionario.getCodigo());
	    final var alterou = comando.executeUpdate();
	    con.commit();
	    final var endTime = System.currentTimeMillis();
	    System.out.println("tempo total desde alterar funcionario " + (endTime-startTime) + "ms"); 
	    return alterou >= 1;
	}
    }

    public boolean excluir(final int id) throws SQLException {
	try (final var con = conexao.getConexao()) {
	    con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando =  con.prepareStatement(SQL_DELETE);
	    comando.setInt(1, id);
	    final var excluiu = comando.executeUpdate() >= 1;
	    con.commit();
	    return excluiu;
	}
    }

}
