package altaqias.ragatanga.capiao.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter @Setter
@Table(name="entidade")
@Entity
@DynamicUpdate(value=true)
@DynamicInsert(value=true)
public class Entidade implements Serializable{
	
	public static final int INTERMEDIADOR = 1;
	public static final int BANCO = 2;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome", columnDefinition="VARCHAR(100)", nullable=false, unique=true)
	private String nome;
	
	@Column(name="categoria", columnDefinition="INT(1)", nullable=false)
	private Integer categoria;
	
	@Column(name="bloqueada", nullable=false)
	private boolean bloqueada;
	
	public String categoriaString(){
		return this.categoria==BANCO?"Banco":"Intermediador";
	}
	
	public String bloqueioString(){
		return this.bloqueada?"Sim":"Não";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bloqueada ? 1231 : 1237);
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Entidade other = (Entidade) obj;
		if (bloqueada != other.bloqueada)
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
