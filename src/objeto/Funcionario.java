package objeto;

public class Funcionario {
    private int codigo;

    private static Funcionario funcionario;

    public static Funcionario getFuncionario() {
	return funcionario;
    }

    public static void setFuncionario(final Funcionario funcionario) {
	Funcionario.funcionario = funcionario;
    }

    public void setCodigo(final int codigo) {
	this.codigo = codigo;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(final String nome) {
	this.nome = nome;
    }

    public String getFuncao() {
	return funcao;
    }

    public void setFuncao(final String funcao) {
	this.funcao = funcao;
    }

    public Boolean getAdministrador() {
	return administrador;
    }

    public void setAdministrador(final Boolean administrador) {
	this.administrador = administrador;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(final String senha) {
	this.senha = senha;
    }

    public int getCodigo() {
	return codigo;
    }

    private String nome;

    public String getUsuario() {
	return usuario;
    }

    public void setUsuario(final String usuario) {
	this.usuario = usuario;
    }

    private String usuario;
    private String funcao;
    private Boolean administrador;
    private String senha;
    private boolean ativo;

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
