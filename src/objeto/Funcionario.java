package objeto;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "funcionario")
@Table(name = "funcionario")
public final class Funcionario {
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
