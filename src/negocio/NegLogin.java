package negocio;

import java.sql.SQLException;

import acessoBD.MariaDB.AcessoBD;
import objeto.Funcionario;

public class NegLogin {
    private final AcessoBD conexao = new AcessoBD();
    private static final String SQL_SEARCH = "SELECT nome, senha, administrador from funcionario WHERE senha = ? and nome = ?;";

    public boolean verificaLogin(Funcionario funcionario) throws SQLException {
	try (final var comando = conexao.getConexao().prepareStatement(SQL_SEARCH)) {
	    comando.setString(1, funcionario.getSenha());
	    comando.setString(2, funcionario.getNome());
	    var resultado = comando.executeQuery();
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
