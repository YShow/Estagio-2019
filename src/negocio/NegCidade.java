package negocio;

import java.sql.Connection;
import java.sql.SQLException;
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
	try (final var con = conexao.getConexao()) {
	    con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_INSERT);
	    comando.setString(1, cidade.getNome());
	    comando.setString(2, cidade.getEstado());
	    final var inseriu = comando.executeUpdate() >= 1;
	    con.commit();
	    return inseriu;
	}
    }

    public List<Cidade> consultar(final String metodo) throws SQLException {
	try (final var con = conexao.getConexao()) {
	    con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_SEARCH);
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
	    return lista;
	}
    }

    public boolean alterar(Cidade cidade) throws SQLException {
	try (final var con = conexao.getConexao()) {
	    con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_UPDATE);
	    comando.setString(1, cidade.getNome());
	    comando.setString(2, cidade.getEstado());
	    comando.setInt(3, cidade.getCodigo());
	    final var alterou = comando.executeUpdate() >= 1;
	    con.commit();
	    return alterou;
	}
    }

    public boolean excluir(int id) throws SQLException {
	try (var con = conexao.getConexao()) {
	    con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    final var comando = con.prepareStatement(SQL_DELETE);
	    comando.setInt(1, id);
	    final var excluiu = comando.executeUpdate() >= 1;
	    con.commit();
	    return excluiu;
	}
    }
}
