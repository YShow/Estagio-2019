package objeto;

public class Funcionario {
    private int codigo;

    private static Funcionario funcionario;

    public static Funcionario getFuncionario() {
	return funcionario;
    }

    public static void setFuncionario(Funcionario funcionario) {
	Funcionario.funcionario = funcionario;
    }

    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getFuncao() {
	return funcao;
    }

    public void setFuncao(String funcao) {
	this.funcao = funcao;
    }

    public Boolean getAdministrador() {
	return administrador;
    }

    public void setAdministrador(Boolean administrador) {
	this.administrador = administrador;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

    public int getCodigo() {
	return codigo;
    }

    private String nome;
    private String funcao;
    private Boolean administrador;
    private String senha;
}
