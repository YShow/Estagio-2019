package objeto;

import lombok.Data;

@Data
public final class Cliente {
	private int codigo;
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;
	private boolean ativo;
	private Cidade cidade;

}
