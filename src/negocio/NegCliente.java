package negocio;

import java.sql.SQLException;
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

    public int inserir(Cliente cliente) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_INSERT)) {
	    /*
	     * `nome` varchar(80) NOT NULL, `CPF` varchar(20) NOT NULL, `endereco`
	     * varchar(40) NOT NULL, `telefone` varchar(20) NOT NULL, `ativo` tinyint(1) NOT
	     * NULL, `id_cidade` int(11) NOT NULL,
	     */
	    comando.setString(1, cliente.getNome());
	    comando.setString(2, cliente.getCPF());
	    comando.setString(3, cliente.getEndereco());
	    comando.setString(4, cliente.getTelefone());
	    comando.setBoolean(5, cliente.getAtivo());
	    comando.setInt(6, cliente.getCidade().getCodigo());
	    return comando.executeUpdate();
	}
    }

    public List<Cliente> consultar(String metodo) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
	    comando.setString(1, '%' + metodo + '%');
	    var result = comando.executeQuery();
	    var lista = new ArrayList<Cliente>();
	    /*
	     * SELECT c.codigo, c.nome, c.CPF, c.endereco, c.telefone, c.ativo,
	     * c.id_cidade,\n" + "ci.nome\n" + "FROM cantagalo.cliente c\n" +
	     * "JOIN cidade ci ON c.id_cidade = ci.codigo" + "WHERE c.nome LIKE ?
	     */
	    while (result.next()) {
		var cliente = new Cliente();
		var cidade = new Cidade();
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

    public int alterar(Cliente cliente) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_UPDATE)) {

	    comando.setString(1, cliente.getNome());
	    comando.setString(2, cliente.getCPF());
	    comando.setString(3, cliente.getEndereco());
	    comando.setString(4, cliente.getTelefone());
	    comando.setBoolean(5, cliente.getAtivo());
	    comando.setInt(6, cliente.getCidade().getCodigo());
	    comando.setInt(7, cliente.getCodigo());
	    return comando.executeUpdate();
	}
    }

    public boolean excluir(int id) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_DELETE)) {
	    comando.setInt(1, id);
	    return comando.executeUpdate() >= 1;
	}
    }
}
