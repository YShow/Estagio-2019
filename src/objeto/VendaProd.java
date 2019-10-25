package objeto;

import lombok.Data;

@Data
public final class VendaProd {
	private Produto produto;
	private Vendas vendas;
	private double precoUnitario;
	private int quantidade;
}
