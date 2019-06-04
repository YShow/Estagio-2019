package acessoBD.MariaDB;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcessoBD {
    private Connection conexao;
    // USUARIO DO BANCO
    private static final String USUARIO = ""; 
    //SENHA DO BANCO
    private static final String SENHA = ""; 
    public Connection getConexao() {
	return this.conexao;
    }

    public AcessoBD() throws Exception {
	try {
	   final var stringConexao = "jdbc:mariadb://localhost:3306/cantagalo"
	   	+ "?user=" + USUARIO + "&password=" + SENHA;

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
