package objeto;

import java.time.LocalDate;

public final class Caixa {
	public LocalDate getData() {
		return data;
	}

	public void setData(final LocalDate data) {
		this.data = data;

	}

	public double getPrecototal() {
		return precototal;
	}

	public void setPrecototal(final double precototal) {
		this.precototal = precototal;
	}

	public double getSaida() {
		return saida;
	}

	public void setSaida(final double saida) {
		this.saida = saida;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(final int cliente) {
		this.cliente = cliente;
	}

	public int getCodigo() {
		return codigo;
	}

	private int codigo;
	private LocalDate data;
	private double precototal;
	private double saida;
	private int cliente;

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(final boolean ativo) {
		this.ativo = ativo;
	}

	private boolean ativo;

	public void setCodigo(final int codigo) {
		this.codigo = codigo;

	}
}
