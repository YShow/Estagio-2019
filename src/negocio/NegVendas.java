package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import acessoBD.MariaDB.AcessoBD;
import objeto.Caixa;
import objeto.Cliente;
import objeto.Produto;
import objeto.Vendas;

public final class NegVendas {
	private final AcessoBD conexao = new AcessoBD();
	private static final String SQL_INSERT = "INSERT INTO cantagalo.vendas\n"
			+ "(cod_cliente, cod_caixa, data_venda, forma_de_pagamento,ativo)\n" + "VALUES(?, ?, ?, ?,?);";
	private static final String SQL_SEARCH = "SELECT codigo, cod_cliente, cod_caixa, data_venda,"
			+ " forma_de_pagamento,ativo\n" + "FROM cantagalo.vendas WHERE data_venda = ? and ativo=true \n";
	private static final String SQL_UPDATE = "UPDATE cantagalo.vendas\n"
			+ "SET cod_cliente=?, cod_caixa=?, data_venda=?, forma_de_pagamento=?, aitvo=? " + "WHERE codigo= ?;\n";
	private static final String SQL_DELETE = "update cantagalo.vendas set ativo=? where codigo = ?";

	private static final String SQL_VENDA_PRODUTO = "INSERT INTO cantagalo.vend_prod(preco_unitario, quantidade, cod_venda, cod_produto)\n"
			+ "VALUES(?,?,?,?);";
	private static final String SQL_CAIXA = "INSERT into caixa(data,preco_total,saida,codigo_cliente,ativo) values(?,?,?,?,?)";
	private static final String SQL_SEARCH_UPDATE = "SELECT v.codigo,v.cod_cliente,vp.cod_produto,v.ativo,\n"
			+ "v.forma_de_pagamento,vp.preco_unitario,vp.quantidade, c.preco_total,c.saida,p.quantidade from vendas v\n"
			+ "JOIN vend_prod vp on vp.cod_venda = v.codigo\n" + "JOIN caixa c on c.codigo_cliente = v.cod_cliente\n"
			+ "join produto p on p.codigo = vp.cod_produto\n" + "WHERE v.codigo = ?;";

	public final boolean inserir(final Vendas vendas) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
		final var insereCaixa = con.prepareStatement(SQL_CAIXA, Statement.RETURN_GENERATED_KEYS);
		final var insereVendaProd = con.prepareStatement(SQL_VENDA_PRODUTO);
		try (con; comando; insereCaixa; insereVendaProd;) {

			// INSERT into caixa(data,preco_total,saida,codigo_cliente,ativo)
			insereCaixa.setObject(1, vendas.getCaixa().getData());
			insereCaixa.setDouble(2, vendas.getCaixa().getPrecototal());
			insereCaixa.setDouble(3, vendas.getCaixa().getSaida());
			insereCaixa.setInt(4, vendas.getCaixa().getCliente());
			insereCaixa.setBoolean(5, vendas.getCaixa().isAtivo());

			insereCaixa.executeUpdate();
			final var caixaCodigo = insereCaixa.getGeneratedKeys();
			int codigoCaixa = 0;
			if (caixaCodigo.next()) {
				codigoCaixa = caixaCodigo.getInt(1);
			}

			/*
			 * "INSERT INTO cantagalo.vendas\n" +
			 * "(cod_cliente, cod_caixa, data_venda, forma_de_pagamento,ativo)\n" +
			 * "VALUES(?, ?, ?, ?,?);";
			 */
			comando.setInt(1, vendas.getCliente().getCodigo());
			comando.setInt(2, codigoCaixa);
			comando.setObject(3, vendas.getData());
			comando.setString(4, vendas.getFormaPagamento());
			comando.setBoolean(5, vendas.isAtivo());
			final var inseriu = comando.executeUpdate() >= 1;

			final var idVenda = comando.getGeneratedKeys();
			int codigoVenda = 0;
			if (idVenda.next()) {
				codigoVenda = idVenda.getInt(1);
			}

			// INSERT INTO cantagalo.vend_prod(preco_unitario, quantidade, cod_venda,
			// cod_produto)\n" +
			// "VALUES(?,?,?,?);
			insereVendaProd.setDouble(1, vendas.getProduto().getPreco());
			insereVendaProd.setInt(2, vendas.getProduto().getQuantidade());
			insereVendaProd.setInt(3, codigoVenda);
			insereVendaProd.setInt(4, vendas.getProduto().getCodigo());
			insereVendaProd.executeUpdate();

			con.commit();
			System.out
					.println("Inserir de Vendas demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return inseriu;

		}
	}

	public final List<Vendas> consultar(final LocalDate metodo) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setReadOnly(true);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_SEARCH);
		try (con; comando;) {
			comando.setObject(1, metodo);
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

	public final boolean alterar(final Vendas vendas) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_UPDATE);
		try (con; comando;) {
			// TODO Fazer alteração de venda
			con.commit();
			System.out
					.println("Alterar de Vendas demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return false;
		}
	}

	public final boolean excluir(final int id) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_DELETE);
		try (con; comando) {
			comando.setBoolean(1, false);
			comando.setInt(2, id);
			final var desativou = comando.executeUpdate() >= 1;
			con.commit();
			System.out
					.println("Excluir de Vendas demorou: " + Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return desativou;
		}
	}

	public final Vendas pegaVendaAlterar(final int id) throws SQLException {
		final var comeco = Instant.now();
		final var con = conexao.getConexao();
		con.setAutoCommit(false);
		con.setReadOnly(true);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		final var comando = con.prepareStatement(SQL_SEARCH_UPDATE);
		try (con; comando;) {
			comando.setInt(1, id);
			final var result = comando.executeQuery();

			/*
			 * SELECT v.codigo,v.cod_cliente,vp.cod_produto,v.ativo,
			 * v.forma_de_pagamento,vp.preco_unitario,vp.quantidade, c.preco_total,c.saida
			 * from vendas v JOIN vend_prod vp on vp.cod_venda = v.codigo JOIN caixa c on
			 * c.codigo_cliente = v.cod_cliente WHERE v.codigo = 101
			 */
			final var venda = new Vendas();
			if (result.next()) {

				final var cliente = new Cliente();
				final var caixa = new Caixa();
				final var produto = new Produto();
				venda.setCodigo(result.getInt("v.codigo"));
				cliente.setCodigo(result.getInt("v.cod_cliente"));

				produto.setCodigo(result.getInt("vp.cod_produto"));
				venda.setAtivo(result.getBoolean("v.ativo"));
				venda.setFormaPagamento(result.getString("v.forma_de_pagamento"));
				produto.setPreco(result.getDouble("vp.preco_unitario"));
				produto.setQuantidade(result.getInt("p.quantidade"));
				caixa.setPrecototal(result.getDouble("c.preco_total"));
				caixa.setSaida(result.getDouble("c.saida"));
				venda.setQuantidadeVendida(result.getInt("vp.quantidade"));
				venda.setCaixa(caixa);
				venda.setProduto(produto);
				venda.setCliente(cliente);

			}
			System.out.println("Consulta de Vendas para alterar demorou: "
					+ Duration.between(comeco, Instant.now()).toMillis() + "ms");
			return venda;
		}

	}
}
