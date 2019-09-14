package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

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
	    + "senhahash =COALESCE(?,senhahash), salt =COALESCE(?,salt) where codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM cantagalo.funcionario\n" + "WHERE codigo=? ;";

    public boolean inserir(final Funcionario funcionario) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	 con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    con.setAutoCommit(false);
	    final var comando = con.prepareStatement(SQL_INSERT);
	   
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
	    System.out.println("Insercao de funcionario demorou: " + 
	    Duration.between(comeco, Instant.now()).toMillis()  + "ms");
	    return inseriu;
	}
    }

    public List<Funcionario> consultar(final String metodo) throws SQLException {
	   final var comeco = Instant.now();
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
		final var funcionario = new Funcionario();
		funcionario.setCodigo(result.getInt("codigo"));
		funcionario.setNome(result.getString("nome"));
		funcionario.setFuncao(result.getString("funcao"));
		funcionario.setAdministrador(result.getBoolean("administrador"));
		
		lista.add(funcionario);
	    }
	 
	    
	    
	    System.out.println("Consulta de funcionario demorou: " + Duration.between(comeco, Instant.now()).toMillis()  + "ms");
	 
	    return lista;
	}
    }

    public boolean alterar(final Funcionario funcionario) throws SQLException {
	final var comeco = Instant.now();
	 final var con = conexao.getConexao();   
	 con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_UPDATE);
	try (con;comando;) {
	    
	    comando.setString(1, funcionario.getNome());
	    comando.setString(2, funcionario.getFuncao());
	    comando.setBoolean(3, funcionario.getAdministrador());
	    if(funcionario.getSenha().isBlank()) {
		
		    comando.setString(4, null);
		    comando.setString(5, null);
	    }else
	    {
		final var salt = Senha.geraSalt();
		    comando.setString(4, Senha.criaSenha(funcionario.getSenha(),salt));
		    comando.setString(5, salt);
	    }
	    
	    comando.setInt(6, funcionario.getCodigo());
	    final var alterou = comando.executeUpdate() >= 1;
	    con.commit();
	   System.out.println("Alteração de funcionario demorou: " 
	    + Duration.between(comeco, Instant.now()).toMillis() + "ms");
	    return alterou;
	}
    }

    public boolean excluir(final int id) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando =  con.prepareStatement(SQL_DELETE);
	try (con;comando;) {
	    
	    comando.setInt(1, id);
	    final var excluiu = comando.executeUpdate() >= 1;
	    con.commit();
	    System.out.println("Exclusao de funcionario demorou: " 
		    + Duration.between(comeco, Instant.now()).toMillis() + "ms");
	    return excluiu;
	}
    }

}
