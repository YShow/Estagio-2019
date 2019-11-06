package objeto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "funcionario")
@Table(name = "funcionario")
public final class Funcionario {
	public final int getCodigo() {
		return codigo;
	}

	public final void setCodigo(final int codigo) {
		this.codigo = codigo;
	}

	public static final Funcionario getFuncionario() {
		return funcionario;
	}

	public static final void setFuncionario(final Funcionario funcionario) {
		Funcionario.funcionario = funcionario;
	}

	public final String getNome() {
		return nome;
	}

	public final void setNome(final String nome) {
		this.nome = nome;
	}

	public final String getUsuario() {
		return usuario;
	}

	public final void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public final String getFuncao() {
		return funcao;
	}

	public final void setFuncao(final String funcao) {
		this.funcao = funcao;
	}

	public final boolean isAdministrador() {
		return administrador;
	}

	public final void setAdministrador(final boolean administrador) {
		this.administrador = administrador;
	}

	public final String getSenha() {
		return senha;
	}

	public final void setSenha(final String senha) {
		this.senha = senha;
	}

	public final String getSalt() {
		return salt;
	}

	public final void setSalt(final String salt) {
		this.salt = salt;
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
	public static Funcionario funcionario;
	private String nome;
	private String usuario;
	private String funcao;
	private boolean administrador;
	@Column(name = "senhahash")
	private String senha;
	private String salt;

	private boolean ativo;
}
