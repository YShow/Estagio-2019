package objeto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "cliente")
@Table(name = "cliente")
public final class Cliente {
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
