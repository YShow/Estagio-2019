package negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.Produto;

public class NegProduto {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "INSERT INTO cantagalo.produto\n" + "(ativo, preco, quantidade, nome)\n"
	    + "VALUES(?, ?, ?, ?);\n" + "";
    private static final String SQL_SEARCH = "SELECT codigo, ativo, preco, quantidade, nome\n"
	    + "FROM cantagalo.produto WHERE nome LIKE ? ";
    private static final String SQL_UPDATE = "UPDATE cantagalo.produto\n"
	    + "SET ativo=?, preco=?, quantidade=?, nome=?\n" + "WHERE codigo=? ;";
    private static final String SQL_DELETE = "";

    public int inserir(Produto produto) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_INSERT)) {
	    comando.setBoolean(1, produto.getAtivo());
	    comando.setDouble(2, produto.getPreco());
	    comando.setInt(3, produto.getQuantidade());
	    comando.setString(4, produto.getNome());

	    return comando.executeUpdate();
	}

    }

    public List<Produto> consultar(String metodo) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
	    comando.setString(1, '%' + metodo + '%');
	    var result = comando.executeQuery();
	    var lista = new ArrayList<Produto>();
	    while (result.next()) {
		var produto = new Produto();
		produto.setAtivo(result.getBoolean("ativo"));
		produto.setNome(result.getString("nome"));
		produto.setPreco(result.getDouble("preco"));
		produto.setQuantidade(result.getInt("quantidade"));
		produto.setCodigo(result.getInt("codigo"));

		lista.add(produto);
	    }
	    return lista;
	}
    }

    public int alterar(Produto produto) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_UPDATE)) {
	    comando.setBoolean(1, produto.getAtivo());
	    comando.setDouble(2, produto.getPreco());
	    comando.setInt(3, produto.getQuantidade());
	    comando.setString(4, produto.getNome());
	    comando.setInt(5, produto.getCodigo());
	    return comando.executeUpdate();
	}
    }

    public boolean excluir(int id) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_DELETE)) {
	    return false;
	}
    }
}
