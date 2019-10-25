package objeto;

import java.time.LocalDate;

import lombok.Data;

@Data
public final class Caixa {

	private int codigo;
	private LocalDate data;
	private double precototal;
	private double saida;
	private int funcionario;
	private boolean ativo;

}
