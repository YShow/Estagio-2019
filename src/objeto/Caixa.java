package objeto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "caixa")
@Table(name = "caixa")
public final class Caixa {

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

	public final double getPrecototal() {
		return precototal;
	}

	public final void setPrecototal(final double precototal) {
		this.precototal = precototal;
	}

	public final double getSaida() {
		return saida;
	}

	public final void setSaida(final double saida) {
		this.saida = saida;
	}

	public final int getFuncionario() {
		return funcionario;
	}

	public final void setFuncionario(final int funcionario) {
		this.funcionario = funcionario;
	}

	public final boolean isAtivo() {
		return ativo;
	}

	public final void setAtivo(final boolean ativo) {
		this.ativo = ativo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private LocalDate data;
	@Column(name = "preco_total")
	private double precototal;
	private double saida;
	@Column(name = "codigo_funcionario")
	private int funcionario;
	private boolean ativo;

}
