package objeto;

public final class Produto {
	public final int getCodigo() {
		return codigo;
	}

	public final void setCodigo(final int codigo) {
		this.codigo = codigo;
	}

	public final boolean isAtivo() {
		return ativo;
	}

	public final void setAtivo(final boolean ativo) {
		this.ativo = ativo;
	}

	public static final int getCodProduto() {
		return codProduto;
	}

	public static final void setCodProduto(final int codProduto) {
		Produto.codProduto = codProduto;
	}

	public final double getPreco() {
		return preco;
	}

	public final void setPreco(final double preco) {
		this.preco = preco;
	}

	public final int getQuantidade() {
		return quantidade;
	}

	public final void setQuantidade(final int quantidade) {
		this.quantidade = quantidade;
	}

	public final String getNome() {
		return nome;
	}

	public final void setNome(final String nome) {
		this.nome = nome;
	}

	private int codigo;
	private boolean ativo;
	public static int codProduto;
	private double preco;
	private int quantidade;
	private String nome;
}
