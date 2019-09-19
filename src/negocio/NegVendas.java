package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.Caixa;
import objeto.Cliente;
import objeto.Vendas;

public class NegVendas {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_INSERT = "INSERT INTO cantagalo.vendas\n"
	    + "(cod_cliente, cod_caixa, data_venda, forma_de_pagamento,ativo)\n" + "VALUES(?, ?, ?, ?,?);";
    private static final String SQL_SEARCH = "SELECT codigo, cod_cliente, cod_caixa, data_venda,"
	    + " forma_de_pagamento,ativo\n" + "FROM cantagalo.vendas WHERE data_venda LIKE ? and ativo=true \n";
    private static final String SQL_UPDATE = "UPDATE cantagalo.vendas\n"
	    + "SET cod_cliente=?, cod_caixa=?, data_venda=?, forma_de_pagamento=?, aitvo=? " + "WHERE codigo= ?;\n";
    private static final String SQL_DELETE = "update cantagalo.vendas set ativo=? where codigo = ?";

    public boolean inserir(final Vendas vendas) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	final var comando = con.prepareStatement(SQL_INSERT);
	try (con; comando;) {
	    /*
	     * "INSERT INTO cantagalo.vendas\n" +
	     * "(cod_cliente, cod_caixa, data_venda, forma_de_pagamento)\n" +
	     * "VALUES(?, ?, ?, ?);";
	     */
	    comando.setInt(1, vendas.getCliente().getCodigo());
	    comando.setInt(2, vendas.getCaixa().getCodigo());
	    comando.setObject(3, vendas.getData());
	    comando.setString(4, vendas.getFormaPagamento());
	    comando.setBoolean(5, true);
	    final var inseriu = comando.executeUpdate() >= 1;
	    con.commit();
	    System.out
		    .println("Inserir de Vendas demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
	    return inseriu;

	}
    }

    public List<Vendas> consultar(final String metodo) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	con.setReadOnly(true);
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	final var comando = con.prepareStatement(SQL_SEARCH);
	try (con; comando;) {
	    comando.setString(1, '%' + metodo + '%');
	    final var result = comando.executeQuery();
	    final var lista = new ArrayList<Vendas>();

	    /*
	     * "SELECT codigo, cod_cliente, cod_caixa, data_venda," +
	     * " forma_de_pagamento\n" + "FROM cantagalo.vendas WHERE data_venda LIKE ? \n";
	     */
	    while (result.next()) {
		final var venda = new Vendas();
		final var cliente = new Cliente();
		final var caixa = new Caixa();
		venda.setCodigo(result.getInt("codigo"));
		cliente.setCodigo(result.getInt("cod_cliente"));
		venda.setCliente(cliente);
		caixa.setCodigo(result.getInt("cod_caixa"));
		venda.setCaixa(caixa);
		venda.setData(result.getObject("data_venda", LocalDate.class));
		venda.setFormaPagamento(result.getString("forma_de_pagamento"));
		venda.setAtivo(result.getBoolean("ativo"));
		lista.add(venda);
	    }
	    System.out.println(
		    "Consulta de Vendas demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
	    return lista;
	}
    }

    public boolean alterar(final Vendas vendas) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	final var comando = con.prepareStatement(SQL_UPDATE);
	try (con; comando;) {

	    con.commit();
	    System.out
		    .println("Alterar de Vendas demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
	    return false;
	}
    }

    public boolean excluir(final int id) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	con.setAutoCommit(false);
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	final var comando = con.prepareStatement(SQL_DELETE);
	try (con; comando) {
		comando.setBoolean(1, false);
		comando.setInt(2, id);
		final var desativou = comando.executeUpdate() >=1;
	    con.commit();
	    System.out
		    .println("Excluir de Vendas demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
	    return desativou;
	}
    }
}
