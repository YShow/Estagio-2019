package objeto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "cidade")
@Table(name = "cidade")
public final class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;

	public final int getCodigo() {
		return codigo;
	}

	public final void setCodigo(final int codigo) {
		this.codigo = codigo;
	}

	public final String getNome() {
		return nome;
	}

	public final void setNome(final String nome) {
		this.nome = nome;
	}

	public final String getEstado() {
		return estado;
	}

	public final void setEstado(final String estado) {
		this.estado = estado;
	}

	public static final int getCodCidade() {
		return codCidade;
	}

	public static final void setCodCidade(final int codCidade) {
		Cidade.codCidade = codCidade;
	}

	private String nome;

	private String estado;

	public static int codCidade;

}
