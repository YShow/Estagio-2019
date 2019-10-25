package objeto;

import java.time.LocalDate;

import lombok.Data;

@Data
public final class Vendas {
	private Produto produto;
	private Cliente cliente;
	private int codigo;
	private LocalDate data;
	private Caixa caixa;
	private boolean ativo;
	private String formaPagamento;
	private int quantidadeVendida;

}
