package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.Funcionario;
import utilidade.Senha;

public class NegFuncionario {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "insert into funcionario(nome,funcao,administrador,senhahash,salt,usuario)"
	    + " values(?,?,?,?,?,?)";
    private static final String SQL_SEARCH = "select codigo,nome,funcao,administrador,senhahash from funcionario"
	    + "  where nome LIKE ? ";
    private static final String SQL_UPDATE = "update funcionario set nome = ?, funcao = ?, administrador = ?,"
	    + "senhahash = ? where codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM cantagalo.funcionario\n" + "WHERE codigo=? ;";

    public boolean inserir(final Funcionario funcionario) throws SQLException {
	
	final var con = conexao.getConexao();
	 con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    con.setAutoCommit(false);
	    var comando = con.prepareStatement(SQL_INSERT);
	   
	try (con;comando;) {	
	    final var salt = Senha.geraSalt();
	    comando.setString(1, funcionario.getNome());
	    comando.setString(2, funcionario.getFuncao());
	    comando.setBoolean(3, funcionario.getAdministrador());
	    comando.setString(4, Senha.criaSenha(funcionario.getSenha(),salt));
	    comando.setString(5, salt);
	    comando.setString(6, "yasser");

	
	   final var inseriu = comando.executeUpdate() >= 1;
	    con.commit();
	
	
	    return inseriu;
	}
    }

    public List<Funcionario> consultar(final String metodo) throws SQLException {
	   final var agora = Instant.now();
	final var con = conexao.getConexao();
	  con.setAutoCommit(false);
	    con.setReadOnly(true);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_SEARCH);
	try (con;comando;) {	  
	    comando.setString(1, '%' + metodo + '%');
	  final  var result = comando.executeQuery();
	    
	   final var lista = new ArrayList<Funcionario>();

	    while (result.next()) {
		var funcionario = new Funcionario();
		funcionario.setCodigo(result.getInt("codigo"));
		funcionario.setNome(result.getString("nome"));
		funcionario.setFuncao(result.getString("funcao"));
		funcionario.setAdministrador(result.getBoolean("administrador"));
		funcionario.setSenha(result.getString("senha"));
		lista.add(funcionario);
	    }
	 
	    final var depois =  Instant.now();
	    
	    System.out.println("tempo de duraçao" + Duration.between(agora, depois).toMillis()  + "ms");
	 
	    return lista;
	}
    }

    public boolean alterar(final Funcionario funcionario) throws SQLException {
	
	 final var con = conexao.getConexao();   
	 con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_UPDATE);
	try (con;comando;) {
	   
	    comando.setString(1, funcionario.getNome());
	    comando.setString(2, funcionario.getFuncao());
	    comando.setBoolean(3, funcionario.getAdministrador());
	    comando.setString(4, funcionario.getSenha());
	    comando.setInt(5, funcionario.getCodigo());
	    final var alterou = comando.executeUpdate() >= 1;
	    con.commit();
	   
	    return alterou;
	}
    }

    public boolean excluir(final int id) throws SQLException {
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando =  con.prepareStatement(SQL_DELETE);
	try (con;comando;) {
	    
	    comando.setInt(1, id);
	    final var excluiu = comando.executeUpdate() >= 1;
	    con.commit();
	    return excluiu;
	}
    }

}
