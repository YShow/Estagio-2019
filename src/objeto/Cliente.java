package objeto;

public class Cliente {
    private int codigo;

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getCPF() {
	return cpf;
    }

    public void setCPF(String cpf) {
	this.cpf = cpf;
    }

    public String getEndereco() {
	return endereco;
    }

    public void setEndereco(String endereco) {
	this.endereco = endereco;
    }

    public String getTelefone() {
	return telefone;
    }

    public void setTelefone(String telefone) {
	this.telefone = telefone;
    }

    public Boolean getAtivo() {
	return ativo;
    }

    public void setAtivo(Boolean ativo) {
	this.ativo = ativo;
    }

    public Cidade getCidade() {
	return cidade;
    }

    public void setCidade(Cidade cidade) {
	this.cidade = cidade;
    }

    public int getCodigo() {
	return codigo;
    }

    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private Boolean ativo;
    private Cidade cidade;
}
