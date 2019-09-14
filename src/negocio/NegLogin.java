package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

import acessoBD.MariaDB.AcessoBD;
import objeto.Funcionario;
import utilidade.Senha;

public class NegLogin {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_SEARCH = "SELECT usuario,salt, senhahash, administrador from funcionario WHERE  usuario = ?;";

    public boolean verificaLogin(final Funcionario funcionario) throws SQLException {
	final var comeco = Instant.now();
	final var con = conexao.getConexao();
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	con.setAutoCommit(false);
	con.setReadOnly(true);
	final var comando = con.prepareStatement(SQL_SEARCH);
	try (con;comando;) {
	   var existeUsuario = false;
	    comando.setString(1, funcionario.getUsuario());
	    final var resultado = comando.executeQuery();
	    if (resultado.next()) {
		
		final var funcionarioPadrao = new Funcionario();
		if(Senha.senhaCorreta(funcionario.getSenha(),resultado.getString("senhahash"), resultado.getString("salt")))
		{
		funcionarioPadrao.setAdministrador(resultado.getBoolean("administrador"));
		Funcionario.setFuncionario(funcionarioPadrao);
		existeUsuario = true;
		}else
		    existeUsuario = true;
	    }
	    System.out.println("Login de usuario demorou: " + 
		    Duration.between(comeco, Instant.now()).toMillis()  + "ms");
	    return existeUsuario;
	}
    }
}
