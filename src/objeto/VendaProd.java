package objeto;

public class VendaProd {
    private Produto produto;
    private Vendas vendas;
    private Double precoUnitario;
    private int quantidade;

    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
    }

    public Vendas getVendas() {
	return vendas;
    }

    public void setVendas(Vendas vendas) {
	this.vendas = vendas;
    }

    public Double getPrecoUnitario() {
	return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
	this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
	return quantidade;
    }

    public void setQuantidade(int quantidade) {
	this.quantidade = quantidade;
    }
}
