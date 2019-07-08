package acessoBD.MariaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessoBD {
    // USUARIO DO BANCO
    private static final String USUARIO = "";
    // SENHA DO BANCO
    private static final String SENHA = "";
    private static final String STRINGCONEXAO = "jdbc:mariadb://localhost:3306/cantagalo" + "?user=" + USUARIO
	    + "&password=" + SENHA;

    public Connection getConexao() throws SQLException {
	return DriverManager.getConnection(STRINGCONEXAO);
    }

}
