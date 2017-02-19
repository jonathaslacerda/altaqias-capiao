package altaqias.ragatanga.capiao.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import altaqias.ragatanga.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@SessionScoped
@ManagedBean(name="SessaoBean")
public class SessaoBean implements Serializable{

	@Getter @Setter
	private Cliente cliente;
	
	public void login(Cliente cliente){
		this.cliente = cliente;
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		session.setAttribute("cliente", this.cliente);
	}

	public void logout(){
		this.cliente = null;
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		session.setAttribute("cliente", this.cliente);
		session.invalidate();
	}

	public void guardar(String chave, Object objeto){
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		session.setAttribute(chave, objeto);
	}

	public void retirar(String chave){
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		session.removeAttribute(chave);
	}

	public Object recuperar(String chave){
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		return session.getAttribute(chave);
	}

	public boolean isLogado(){
		return this.cliente != null;
	}

}
