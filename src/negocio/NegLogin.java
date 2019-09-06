package negocio;

import java.sql.Connection;
import java.sql.SQLException;

import acessoBD.MariaDB.AcessoBD;
import objeto.Funcionario;

public class NegLogin {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_SEARCH = "SELECT nome, senha, administrador from funcionario WHERE senha = ? and nome = ?;";

    public boolean verificaLogin(final Funcionario funcionario) throws SQLException {
	final var con = conexao.getConexao();
	con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	con.setAutoCommit(false);
	con.setReadOnly(true);
	final var comando = con.prepareStatement(SQL_SEARCH);
	try (con;comando;) {
	    comando.setString(1, funcionario.getSenha());
	    comando.setString(2, funcionario.getNome());
	    final var resultado = comando.executeQuery();
	    if (resultado.next()) {
		final var funcionarioPadrao = new Funcionario();
		funcionarioPadrao.setAdministrador(resultado.getBoolean("administrador"));
		Funcionario.setFuncionario(funcionarioPadrao);
		return true;
	    } else
		return false;
	}
    }
}
