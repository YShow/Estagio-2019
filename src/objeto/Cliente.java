package objeto;

public class Cliente {
    private int codigo;

    public String getNome() {
	return nome;
    }

    public void setNome(final String nome) {
	this.nome = nome;
    }

    public String getCPF() {
	return cpf;
    }

    public void setCPF(final String cpf) {
	this.cpf = cpf;
    }

    public String getEndereco() {
	return endereco;
    }

    public void setEndereco(final String endereco) {
	this.endereco = endereco;
    }

    public String getTelefone() {
	return telefone;
    }

    public void setTelefone(final String telefone) {
	this.telefone = telefone;
    }

    public Boolean getAtivo() {
	return ativo;
    }

    public void setAtivo(final Boolean ativo) {
	this.ativo = ativo;
    }

    public Cidade getCidade() {
	return cidade;
    }

    public void setCidade(final Cidade cidade) {
	this.cidade = cidade;
    }

    public int getCodigo() {
	return codigo;
    }

    public void setCodigo(final int codigo) {
	this.codigo = codigo;
    }

  

    private String nome;

    private String cpf;
    private String endereco;
    private String telefone;
    private Boolean ativo;
    private Cidade cidade;
}
