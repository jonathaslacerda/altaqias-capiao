package altaqias.ragatanga.capiao.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.google.gson.JsonSyntaxException;

import altaqias.ragatanga.apiclient.control.ClienteController;
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
			ClienteAutenticarResponse response = ClienteController.autenticar(this.email, this.senha);
			if(response.getMensagem().getCodigo().equals(MensagemTO.CODIGO_SUCESSO)){
				this.sessao.setCliente(response.getCliente());
				return "home?faces-redirect=true";
			}
			FacesUtils.alertGrow(response.getMensagem().getMensagem());
		}catch(JsonSyntaxException e){
			e.printStackTrace();
			FacesUtils.alertGrow(e.getMessage());
		}
		return "";
	}
}
