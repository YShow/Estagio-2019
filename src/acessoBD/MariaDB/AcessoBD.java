package acessoBD.MariaDB;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcessoBD {
    private Connection conexao;

    public Connection getConexao() {
	return this.conexao;
    }

    public AcessoBD() throws Exception {
	try {
	    String stringConexao = "jdbc:mysql://localhost:3306/cantagalo" + "?user=teste&password=1234";

	    this.conexao = DriverManager.getConnection(stringConexao);
	} catch (Exception ex) {
	    throw new Exception("Falha ao abrir conexão com o banco de" + " dados. Detalhes: " + ex.toString());
	}
    }

    public boolean executarComando(PreparedStatement comando) throws SQLException {
	return (comando.executeUpdate() > 0);
    }

    public ResultSet executarConsulta(PreparedStatement comando) throws SQLException {
	return comando.executeQuery();
    }
}
