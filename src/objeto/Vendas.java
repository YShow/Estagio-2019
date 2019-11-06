package objeto;

import java.time.LocalDate;

public final class Vendas {
	private Produto produto;

	public final Produto getProduto() {
		return produto;
	}

	public final void setProduto(final Produto produto) {
		this.produto = produto;
	}

	public final Cliente getCliente() {
		return cliente;
	}

	public final void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}

	public final int getCodigo() {
		return codigo;
	}

	public final void setCodigo(final int codigo) {
		this.codigo = codigo;
	}

	public final LocalDate getData() {
		return data;
	}

	public final void setData(final LocalDate data) {
		this.data = data;
	}

	public final Caixa getCaixa() {
		return caixa;
	}

	public final void setCaixa(final Caixa caixa) {
		this.caixa = caixa;
	}

	public final boolean isAtivo() {
		return ativo;
	}

	public final void setAtivo(final boolean ativo) {
		this.ativo = ativo;
	}

	public final String getFormaPagamento() {
		return formaPagamento;
	}

	public final void setFormaPagamento(final String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public final int getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public final void setQuantidadeVendida(final int quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	private Cliente cliente;
	private int codigo;
	private LocalDate data;
	private Caixa caixa;
	private boolean ativo;
	private String formaPagamento;
	private int quantidadeVendida;

}
