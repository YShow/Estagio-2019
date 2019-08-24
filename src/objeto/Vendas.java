package objeto;

import java.time.LocalDate;

public class Vendas {
    private Produto produto;
    private Cliente cliente;
    private int codigo;
    private LocalDate data;
    private Caixa caixa;

    public Caixa getCaixa() {
	return caixa;
    }

    public void setCaixa(Caixa caixa) {
	this.caixa = caixa;
    }

    private String formaPagamento;

    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public LocalDate getData() {
	return data;
    }

    public void setData(LocalDate data) {
	this.data = data;
    }

    public String getFormaPagamento() {
	return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
	this.formaPagamento = formaPagamento;
    }

    public int getCodigo() {
	return codigo;
    }

    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

}
