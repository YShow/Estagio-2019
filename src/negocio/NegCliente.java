package negocio;

import acessoBD.MariaDB.AcessoBD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objeto.Cliente;

public class NegCliente {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "insert into cliente(nome,CPF,endereco,telefone,ativo,id_cidade)"
	    + " values(?,?,?,?,?,?)";
    private static final String SQL_SEARCH = "select nome,CPF,endereco,telefone,ativo,id_cidade from cliente"
	    + "where codigo = ?";
    private static final String SQL_UPDATE = "update cliente set nome = ?, CPF = ?, endereco = ?"
	    + "telefone = ?, ativo = ?, id_cidade = ? where codigo = ?";
    private static final String SQL_DELETE = "";

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
	    comando.setInt(6, cliente.getCidade());
	    return comando.executeUpdate();
	}
    }

    public List<Cliente> consultar(String metodo) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
	    var result = comando.executeQuery();
	    var lista = new ArrayList<Cliente>();
	    while (result.next()) {
		var cliente = new Cliente();
		cliente.setCodigo(result.getInt("codigo"));
		cliente.setNome(result.getString("nome"));
		cliente.setCPF(result.getString("CPF"));
		cliente.setEndereco(result.getString("endereco"));
		cliente.setTelefone(result.getString("telefone"));
		lista.add(cliente);
	    }
	    return lista;
	}
    }

    public int alterar(Cliente cliente) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_UPDATE)) {

	    /*
	     * "update cliente set nome = ?, CPF = ?, endereco = ?" +
	     * "telefone = ?, ativo = ?, id_cidade = ? where codigo = ?"
	     */
	    comando.setString(1, cliente.getNome());
	    comando.setString(2, cliente.getCPF());
	    comando.setString(3, cliente.getEndereco());
	    comando.setString(4, cliente.getTelefone());
	    comando.setBoolean(5, cliente.getAtivo());
	    comando.setInt(6, cliente.getCidade());
	    comando.setInt(7, cliente.getCodigo());
	    return comando.executeUpdate();
	}
    }

    public boolean excluir(int id) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_DELETE)) {
	    return false;
	}
    }
}
