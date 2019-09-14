package objeto;

public class Cidade {
    private String nome;

    public String getNome() {
	return nome;
    }

    public void setNome(final String nome) {
	this.nome = nome;
    }

    public void setCodigo(final int codigo) {
	this.codigo = codigo;
    }

    public String getEstado() {
	return estado;
    }

    public void setEstado(final String estado) {
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
    private static int codCidade;
    private static String nomeCidade;
}
