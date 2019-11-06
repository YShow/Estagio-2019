package objeto;

public final class VendaProd {
	private Produto produto;

	public final Produto getProduto() {
		return produto;
	}

	public final void setProduto(final Produto produto) {
		this.produto = produto;
	}

	public final Vendas getVendas() {
		return vendas;
	}

	public final void setVendas(final Vendas vendas) {
		this.vendas = vendas;
	}

	public final double getPrecoUnitario() {
		return precoUnitario;
	}

	public final void setPrecoUnitario(final double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public final int getQuantidade() {
		return quantidade;
	}

	public final void setQuantidade(final int quantidade) {
		this.quantidade = quantidade;
	}

	private Vendas vendas;
	private double precoUnitario;
	private int quantidade;
}
