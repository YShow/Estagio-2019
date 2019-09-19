package objeto;

public class VendaProd {
	private Produto produto;
	private Vendas vendas;
	private Double precoUnitario;
	private int quantidade;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(final Produto produto) {
		this.produto = produto;
	}

	public Vendas getVendas() {
		return vendas;
	}

	public void setVendas(final Vendas vendas) {
		this.vendas = vendas;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(final Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(final int quantidade) {
		this.quantidade = quantidade;
	}
}
