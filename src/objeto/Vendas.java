package objeto;

import java.time.LocalDate;

public class Vendas {
	private Produto produto;
	private Cliente cliente;
	private int codigo;
	private LocalDate data;
	private Caixa caixa;
	private boolean ativo;

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(final Caixa caixa) {
		this.caixa = caixa;
	}

	private String formaPagamento;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(final Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(final LocalDate data) {
		this.data = data;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(final String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(final int codigo) {
		this.codigo = codigo;
	}

}
