package objeto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "cliente")
@Table(name = "cliente")
public final class Cliente {
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

	public final String getCpf() {
		return cpf;
	}

	public final void setCpf(final String cpf) {
		this.cpf = cpf;
	}

	public final String getEndereco() {
		return endereco;
	}

	public final void setEndereco(final String endereco) {
		this.endereco = endereco;
	}

	public final String getTelefone() {
		return telefone;
	}

	public final void setTelefone(final String telefone) {
		this.telefone = telefone;
	}

	public final boolean isAtivo() {
		return ativo;
	}

	public final void setAtivo(final boolean ativo) {
		this.ativo = ativo;
	}

	public final Cidade getCidade() {
		return cidade;
	}

	public final void setCidade(final Cidade cidade) {
		this.cidade = cidade;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;

	private String nome;
	@Column(name = "CPF")
	private String cpf;
	private String endereco;
	private String telefone;
	private boolean ativo;
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;

}
