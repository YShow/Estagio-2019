package objeto;

public final class Produto {
	private int codigo;

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(final boolean ativo) {
		this.ativo = ativo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(final Double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(final int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(final int codigo) {
		this.codigo = codigo;
	}

	public static int CodProdutoGet() {
		return codProduto;
	}

	private boolean ativo;
	public static int codProduto;
	private Double preco;
	private int quantidade;
	private String nome;
}
