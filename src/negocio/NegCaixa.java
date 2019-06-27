package negocio;

import static acessoBD.MariaDB.AcessoBD.getConexao;

import java.util.ArrayList;

import objeto.Caixa;

public class NegCaixa {
    private static final String SQL_INSERT = "INSERT INTO cantagalo.caixa\n"
	    + "(`data`, preco_total, saida, codigo_cliente)" + "VALUES(?, ?, ?, ?)";
    private static final String SQL_SEARCH = "SELECT codigo, data, preco_total, saida, codigo_cliente "
	    + "FROM cantagalo.caixa where data = ?";
    private static final String SQL_UPDATE = "UPDATE cantagalo.caixa "
	    + "SET data= ?, preco_total=?, saida= ?, codigo_cliente= ?" + "WHERE codigo= ?;";
    private static final String SQL_DELETE = "";

    public boolean inserir(Caixa caixa) throws Exception {
	var comando = getConexao().prepareStatement(SQL_INSERT);
	comando.setString(1, caixa.getData().toString());
	comando.setDouble(2, caixa.getPrecototal());
	comando.setDouble(3, caixa.getSaida());
	comando.setInt(4, caixa.getCliente().getCodigo());

	return comando.execute();
    }

    public ArrayList<Caixa> consultar(String metodo) throws Exception {
	var comando = getConexao().prepareStatement(SQL_SEARCH);
	comando.setString(1, metodo);
	var caixa = comando.executeQuery();

	return null;
    }

    public boolean alterar(Caixa caixa) throws Exception {
	var comando = getConexao().prepareStatement(SQL_UPDATE);

	return false;
    }

    public boolean excluir(int id) throws Exception {
	var comando = getConexao().prepareStatement(SQL_DELETE);
	return false;
    }
}
