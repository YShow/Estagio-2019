package objeto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "caixa")
@Table(name = "caixa")
public final class Caixa {

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
