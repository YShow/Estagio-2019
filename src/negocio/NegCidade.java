package negocio;

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
    private static final String SQL_DELETE = "";

    public boolean inserir(Cidade cidade) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_INSERT)) {
	    comando.setString(1, cidade.getNome());
	    comando.setString(2, cidade.getEstado());

	    return comando.executeUpdate() >= 1;
	}
    }

    public List<Cidade> consultar(String metodo) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
	    comando.setString(1, '%' + metodo + '%');
	    var result = comando.executeQuery();
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

    public int alterar(Cidade cidade) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_UPDATE)) {
	    comando.setString(1, cidade.getNome());
	    comando.setString(2, cidade.getEstado());
	    comando.setInt(3, cidade.getCodigo());

	    return comando.executeUpdate();
	}
    }

    public boolean excluir(int id) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_DELETE)) {
	    return false;
	}
    }
}
