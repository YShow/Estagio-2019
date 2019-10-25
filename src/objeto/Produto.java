package objeto;

import lombok.Data;

@Data
public final class Produto {
	private int codigo;
	private boolean ativo;
	public static int codProduto;
	private double preco;
	private int quantidade;
	private String nome;
}
