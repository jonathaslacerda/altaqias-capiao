package altaqias.ragatanga.capiao.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import altaqias.ragatanga.apiclient.control.ClienteController;
import altaqias.ragatanga.model.Cliente;
import altaqias.ragatanga.to.ClienteCadastrarResponse;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean(name="RegistroClienteBean")
public class RegistroClienteBean {

	@Getter @Setter
	private Cliente cliente;

	@Getter @Setter
	private String senha;

	@PostConstruct
	private void init(){
		this.cliente = new Cliente();
	}

	public void registrar(){
		try{
			ClienteCadastrarResponse response = ClienteController.cadastrar(this.cliente);
			System.out.println(response.getCliente().getId());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
