package altaqias.ragatanga.capiao.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("serial")
@Table(name="usuario")
@Entity
@DynamicUpdate(value=true)
@DynamicInsert(value=true)
public class Usuario implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private int id;

	@Column(name="cpf", length=11)
	@Getter @Setter
	private String cpf;
	
	@Column(name="senha", columnDefinition="CHAR(64)")
	@Getter @Setter
	private String senha;
	
	@Column(name="nome", columnDefinition="VARCHAR(100)")
	@Getter @Setter
	private String nome;
	
	@Column(name="apelido", columnDefinition="VARCHAR(50)")
	@Getter @Setter
	private String apelido;
	
	@Column(name="email", columnDefinition="VARCHAR(100)")
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	@OneToMany(mappedBy="usuario")
	private List<Telefone> telefones;
	
	@Getter @Setter
	@OneToMany(mappedBy="usuario")
	private List<Vinculo> vinculos;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apelido == null) {
			if (other.apelido != null)
				return false;
		} else if (!apelido.equals(other.apelido))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}
