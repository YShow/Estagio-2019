package objeto;

public class Cidade {
    private String nome;

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    public String getEstado() {
	return estado;
    }

    public void setEstado(String estado) {
	this.estado = estado;
    }

    public int getCodigo() {
	return codigo;
    }

    public static int CodCidadeGet() {
	return codCidade;
    }

    public static String NomeCidadeGet() {
	return nomeCidade;
    }

    private String estado;
    private int codigo;
    public static int codCidade;
    public static String nomeCidade;
}
