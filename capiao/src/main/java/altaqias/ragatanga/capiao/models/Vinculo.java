package altaqias.ragatanga.capiao.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Table(name="vinculo", uniqueConstraints={
		@UniqueConstraint(columnNames={"entidade","usuario"})
})
@DynamicUpdate(value=true)
@DynamicInsert(value=true)
@Entity
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vinculo implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter @Setter
	private int id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="usuario")
	@Getter @Setter
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE})
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name="entidade")
	@Getter @Setter
	private Entidade entidade;

	@Column(name="cargo", columnDefinition="VARCHAR(50)")
	@Getter @Setter
	private String cargo;

	@Column(name="bloqueado", columnDefinition="TINYINT(1)")
	@Getter @Setter
	private boolean bloqueado;

	public Vinculo(){
		this.usuario = new Usuario();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bloqueado ? 1231 : 1237);
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((entidade == null) ? 0 : entidade.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Vinculo other = (Vinculo) obj;
		if (bloqueado != other.bloqueado)
			return false;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (entidade == null) {
			if (other.entidade != null)
				return false;
		} else if (!entidade.equals(other.entidade))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
