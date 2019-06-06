package acessoBD.MariaDB;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class AcessoBD {
    private Connection conexao;
    // USUARIO DO BANCO
    private static final String USUARIO = "";
    // SENHA DO BANCO
    private static final String SENHA = "";
    private static final String stringConexao = "jdbc:mariadb://localhost:3306/cantagalo" + "?user=" + USUARIO + "&password=" + SENHA;

    public Connection getConexao() throws SQLException {
	this.conexao = DriverManager.getConnection(stringConexao);
	return this.conexao;
    }

}
