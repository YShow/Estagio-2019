package acessoBD.MariaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessoBD {
	// USUARIO DO BANCO
	private static final String USUARIO = "usuario";
	// SENHA DO BANCO
	private static final String SENHA = "1234";
	private static final  String STRINGCONEXAO = "jdbc:mariadb://localhost:3306/cantagalo?rewriteBatchedStatements=true";

	public static Connection getConexao() throws SQLException {

		return DriverManager.getConnection(STRINGCONEXAO, USUARIO, SENHA);
	}

}
