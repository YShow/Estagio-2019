package negocio;

import acessoBD.MariaDB.AcessoBD;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import objeto.Caixa;
import objeto.Cliente;
import objeto.Vendas;

public class NegVendas {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "INSERT INTO cantagalo.vendas\n" + 
    	"(cod_cliente, cod_caixa, data_venda, forma_de_pagamento)\n" + 
    	"VALUES(?, ?, ?, ?);";
    private static final String SQL_SEARCH = "SELECT codigo, cod_cliente, cod_caixa, data_venda,"
    	+ " forma_de_pagamento\n" + 
    	"FROM cantagalo.vendas WHERE data_venda LIKE ? \n";
    private static final String SQL_UPDATE = "UPDATE cantagalo.vendas\n" + 
    	"SET cod_cliente=0, cod_caixa=0, data_venda='', forma_de_pagamento=''\n" + 
    	"WHERE codigo=0;\n";
    private static final String SQL_DELETE = "DELETE FROM cantagalo.vendas\n" + 
    	"WHERE codigo=0;\n" + 
    	"";

    public int inserir(Vendas vendas) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_INSERT)) {
	    /*"INSERT INTO cantagalo.vendas\n" + 
		    	"(cod_cliente, cod_caixa, data_venda, forma_de_pagamento)\n" + 
		    	"VALUES(?, ?, ?, ?);";*/
	    comando.setInt(1, vendas.getCliente().getCodigo());
	    comando.setInt(2, vendas.getCaixa().getCodigo());
	    comando.setObject(3, vendas.getData());
	    comando.setString(4, vendas.getFormaPagamento());
	    return comando.executeUpdate();

	}
    }

    public List<Vendas> consultar(String metodo) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
	    comando.setString(1, '%'+ metodo + '%');
	    var result = comando.executeQuery();
	    var lista = new ArrayList<Vendas>();
	    
	    /*
	     "SELECT codigo, cod_cliente, cod_caixa, data_venda,"
    	+ " forma_de_pagamento\n" + 
    	"FROM cantagalo.vendas WHERE data_venda LIKE ? \n";
	     */
	    while (result.next()) {
		var venda = new Vendas();
		var cliente = new Cliente();
		var caixa = new Caixa();
		venda.setCodigo(result.getInt("codigo"));
		cliente.setCodigo(result.getInt("cod_cliente"));
		venda.setCliente(cliente);
		caixa.setCodigo(result.getInt("cod_caixa"));
		venda.setCaixa(caixa);
		venda.setData(result.getObject("data_venda",LocalDate.class));
		venda.setFormaPagamento(result.getString("forma_de_pagamento"));
		lista.add(venda);
	    }
	    return lista;
	}
    }

    public boolean alterar(Vendas vendas) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_UPDATE)) {
	    return false;
	}
    }

    public boolean excluir(int id) throws SQLException {
	try (var comando = conexao.getConexao().prepareStatement(SQL_DELETE)) {
	    return false;
	}
    }
}
