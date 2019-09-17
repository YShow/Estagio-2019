package acessoBD.MariaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessoBD {
    // USUARIO DO BANCO
    
    public Connection getConexao() throws SQLException {
	final var USUARIO = "usuario";
	    // SENHA DO BANCO
	final var SENHA = "1234";
	final var STRINGCONEXAO = "jdbc:mariadb://localhost:3306/cantagalo?rewriteBatchedStatements=true";

	
	return DriverManager.getConnection(STRINGCONEXAO, USUARIO, SENHA);
    }

}
