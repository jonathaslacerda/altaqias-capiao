package altaqias.ragatanga.capiao.beans;

import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.google.gson.JsonSyntaxException;

import altaqias.ragatanga.apiclient.control.ClienteController;
import altaqias.ragatanga.capiao.utils.CryptUtils;
import altaqias.ragatanga.capiao.utils.FacesUtils;
import altaqias.ragatanga.to.ClienteAutenticarResponse;
import altaqias.ragatanga.to.MensagemTO;
import lombok.Getter;
import lombok.Setter;

@RequestScoped
@ManagedBean(name="LoginBean")
public class LoginBean {

	@Getter @Setter
	private String email;
	@Getter @Setter
	private String senha;
	
	@ManagedProperty(value="#{SessaoBean}")
	@Getter @Setter
	private SessaoBean sessao;

	public String logar(){
		try{
			String senhaMD5 = CryptUtils.toMD5(this.senha);
			ClienteAutenticarResponse response = ClienteController.autenticar(this.email, senhaMD5);
			if(response.getMensagem().getCodigo().equals(MensagemTO.CODIGO_SUCESSO)){
				this.sessao.setCliente(response.getCliente());
				return "home?faces-redirect=true";
			}
			FacesUtils.alertGrow(response.getMensagem().getMensagem());
		}catch(JsonSyntaxException | NoSuchAlgorithmException e){
			e.printStackTrace();
			FacesUtils.alertGrow(e.getMessage());
		}
		return "";
	}
	
	public String sair(){
		this.sessao.logout();
		return "../login?faces-redirect=true";
	}
}
