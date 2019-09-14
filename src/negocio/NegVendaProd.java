package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.VendaProd;

public class NegVendaProd {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "";
    private static final String SQL_SEARCH = "";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "";

    public boolean inserir(final VendaProd vendaProd) throws SQLException {
	final var comeco = Instant.now();
	
	final var con = conexao.getConexao();
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	con.setAutoCommit(false);
	final var comando = con.prepareStatement(SQL_INSERT);
	try (con;comando;) {
	    
	    
	    con.commit();
	    System.out.println("Inserir de VendaProduto demorou: " + 
		    Duration.between(comeco, Instant.now()).toMillis()  + "ms");
	    return false;
	}
    }

    public List<VendaProd> consultar(final String metodo) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	con.setAutoCommit(false);
	con.setReadOnly(true);
	final var comando = con.prepareStatement(SQL_SEARCH);
	try (con;comando;) {
	  final  var result = comando.executeQuery();
	    final var lista = new ArrayList<VendaProd>();
	    while (result.next()) {
		final var vendaProd = new VendaProd();
		//
		//
		//
		lista.add(vendaProd);
	    }
	    System.out.println("Consultar de VendaProduto demorou: " + 
		    Duration.between(comeco, Instant.now()).toMillis()  + "ms");
	    return lista;
	}
    }

    public boolean alterar(final VendaProd vendaProd) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	con.setAutoCommit(false);
	final var comando = con.prepareStatement(SQL_UPDATE);
	try (con;comando;) {
	    
	    con.commit();
	    System.out.println("Alterar de VendaProduto demorou: " + 
		    Duration.between(comeco, Instant.now()).toMillis()  + "ms");
	    return false;
	}
    }

    public boolean excluir(final int id) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	final var comando = con.prepareStatement(SQL_DELETE);
	try (con;comando;) {
	    
	    con.commit();
	    System.out.println("Excluir de VendaProduto demorou: " + 
		    Duration.between(comeco, Instant.now()).toMillis()  + "ms");
	    return false;
	}
    }
}
