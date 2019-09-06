package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.Cidade;
import objeto.Cliente;

public class NegCliente {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "insert into cliente(nome,CPF,endereco,telefone,ativo,id_cidade)"
	    + " values(?,?,?,?,?,?)";
    private static final String SQL_SEARCH = "SELECT c.codigo, c.nome, c.CPF, c.endereco, c.telefone, c.ativo, c.id_cidade,\n"
	    + "ci.nome\n" + "FROM cantagalo.cliente c\n" + "JOIN cidade ci ON c.id_cidade = ci.codigo\n"
	    + "WHERE c.nome LIKE ?;";
    private static final String SQL_UPDATE = "update cliente set nome = ?, CPF = ?, endereco = ?,"
	    + "telefone = ?, ativo = ?, id_cidade = ? where codigo = ?;";
    private static final String SQL_DELETE = "DELETE FROM cantagalo.cliente\n" + "WHERE codigo=?;";

    public boolean inserir(final Cliente cliente) throws SQLException {
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	final var comando = con.prepareStatement(SQL_INSERT);
	try (con;comando;) {
	    comando.setString(1, cliente.getNome());
	    comando.setString(2, cliente.getCPF());
	    comando.setString(3, cliente.getEndereco());
	    comando.setString(4, cliente.getTelefone());
	    comando.setBoolean(5, cliente.getAtivo());
	    comando.setInt(6, cliente.getCidade().getCodigo());
	    final var inseriu = comando.executeUpdate() >= 1;
	    con.commit();
	    return inseriu;
	} finally {
	    System.out.println(con.isClosed());
	}
    }

    public List<Cliente> consultar(final String metodo) throws SQLException {
	
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	con.setReadOnly(true);
	final var comando = con.prepareStatement(SQL_SEARCH);	
	try (con; comando;) {
	    
	    comando.setString(1, '%' + metodo + '%');
	    final var lista = new ArrayList<Cliente>();
	    final  var   result = comando.executeQuery();
	   
	    while (result.next()) {
		final var cliente = new Cliente();
		final var cidade = new Cidade();
		cliente.setCodigo(result.getInt("c.codigo"));
		cliente.setNome(result.getString("c.nome"));
		cliente.setCPF(result.getString("c.CPF"));
		cliente.setEndereco(result.getString("c.endereco"));
		cliente.setTelefone(result.getString("c.telefone"));
		cliente.setAtivo(result.getBoolean("c.ativo"));
		
		cidade.setNome(result.getString("ci.nome"));
		cidade.setCodigo(result.getInt("c.id_cidade"));

		cliente.setCidade(cidade);
		lista.add(cliente);
	     }
	    
	    return lista;
	}

    }

    public boolean alterar(final Cliente cliente) throws SQLException {
	final var con = conexao.getConexao();
	final var comando = con.prepareStatement(SQL_UPDATE);
	try (con;comando) {
	    con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    comando.setString(1, cliente.getNome());
	    comando.setString(2, cliente.getCPF());
	    comando.setString(3, cliente.getEndereco());
	    comando.setString(4, cliente.getTelefone());
	    comando.setBoolean(5, cliente.getAtivo());
	    comando.setInt(6, cliente.getCidade().getCodigo());
	    comando.setInt(7, cliente.getCodigo());
	    final var alterou = comando.executeUpdate() >= 1;
	    con.commit();

	    return alterou;
	}
    }

    public boolean excluir(final int id) throws SQLException {
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	final var comando = con.prepareStatement(SQL_DELETE);
	try (con;comando;) {	    
	    comando.setInt(1, id);
	    final var excluiu = comando.executeUpdate() >= 1;
	    con.commit();
	    return excluiu;
	}
    }
}
