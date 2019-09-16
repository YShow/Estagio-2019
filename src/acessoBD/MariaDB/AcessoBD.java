package acessoBD.MariaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessoBD {
    // USUARIO DO BANCO
    
    public Connection getConexao() throws SQLException {
	final String USUARIO = "usuario";
	    // SENHA DO BANCO
	final String SENHA = "1234";
	final String STRINGCONEXAO = "jdbc:mariadb://localhost:3306/cantagalo?rewriteBatchedStatements=true";

	
	return DriverManager.getConnection(STRINGCONEXAO, USUARIO, SENHA);
    }

}
