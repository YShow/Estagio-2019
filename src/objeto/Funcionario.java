package objeto;

import lombok.Data;

@Data
public final class Funcionario {
	private int codigo;
	public static Funcionario funcionario;
	private String nome;
	private String usuario;
	private String funcao;
	private boolean administrador;
	private String senha;
	private boolean ativo;
}
