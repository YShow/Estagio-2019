package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.Cidade;

public class NegCidade {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "INSERT INTO cidade(nome,estado) values(?,?)";
    private static final String SQL_SEARCH = "select codigo,nome,estado from cidade where nome LIKE ? ";
    private static final String SQL_UPDATE = "update cidade set nome = ?, estado = ? where codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM cantagalo.cidade\n" + "WHERE codigo = ? ;";

    public boolean inserir(final Cidade cidade) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_INSERT);
	try (con;comando;) {
	    
	    comando.setString(1, cidade.getNome());
	    comando.setString(2, cidade.getEstado());
	    final var inseriu = comando.executeUpdate() >= 1;
	    con.commit();
	    System.out.println("Insercao de cidade demorou: " + 
		    Duration.between(comeco, Instant.now()).toMillis()  + "ms");
	    return inseriu;
	}
    }

    public List<Cidade> consultar(final String metodo) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    con.setReadOnly(true);
	    final var comando = con.prepareStatement(SQL_SEARCH);
	try (con;comando;) {
	    
	    comando.setString(1, '%' + metodo + '%');
	    var result = comando.executeQuery();
	   // con.commit();
	    var lista = new ArrayList<Cidade>();
	    while (result.next()) {
		var cidade = new Cidade();
		cidade.setCodigo(result.getInt("codigo"));
		cidade.setNome(result.getString("nome"));
		cidade.setEstado(result.getString("estado"));
		lista.add(cidade);
	    }
	    System.out.println("Consulta de cidade demorou: " + 
		    Duration.between(comeco, Instant.now()).toMillis()  + "ms");
	    return lista;
	}
    }

    public boolean alterar(final Cidade cidade) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	 con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_UPDATE);
	try (con;comando;) {
	   
	    comando.setString(1, cidade.getNome());
	    comando.setString(2, cidade.getEstado());
	    comando.setInt(3, cidade.getCodigo());
	    final var alterou = comando.executeUpdate() >= 1;
	    con.commit();
	    System.out.println("Altera de cidade demorou: " + 
		    Duration.between(comeco, Instant.now()).toMillis()  + "ms");
	    return alterou;
	}
    }

    public boolean excluir(final int id) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	 con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_DELETE);
	try (con;comando) {
	   
	    comando.setInt(1, id);
	    final var excluiu = comando.executeUpdate() >= 1;
	    con.commit();
	    System.out.println("Excluir de cidade demorou: " + 
		    Duration.between(comeco, Instant.now()).toMillis()  + "ms");
	    return excluiu;
	}
    }
}
