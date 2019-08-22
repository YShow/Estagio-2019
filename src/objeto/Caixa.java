package objeto;

import java.time.LocalDate;

public class Caixa {
    public LocalDate getData() {
	return data;
    }

    public void setData(LocalDate data) {
	this.data = data;

    }

    public Double getPrecototal() {
	return precototal;
    }

    public void setPrecototal(Double precototal) {
	this.precototal = precototal;
    }

    public Double getSaida() {
	return saida;
    }

    public void setSaida(Double saida) {
	this.saida = saida;
    }

    public int getCliente() {
	return cliente;
    }

    public void setCliente(int cliente) {
	this.cliente = cliente;
    }

    public int getCodigo() {
	return codigo;
    }

    private int codigo;
    private LocalDate data;
    private Double precototal;
    private Double saida;
    private int cliente;

    public void setCodigo(int codigo) {
	this.codigo = codigo;

    }
}
