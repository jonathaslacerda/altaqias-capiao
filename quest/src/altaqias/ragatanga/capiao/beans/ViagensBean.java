package altaqias.ragatanga.capiao.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import altaqias.ragatanga.apiclient.control.QuestController;
import altaqias.ragatanga.capiao.utils.FacesUtils;
import altaqias.ragatanga.model.Inscricao;
import altaqias.ragatanga.model.Quest;
import altaqias.ragatanga.to.MensagemTO;
import altaqias.ragatanga.to.QuestEntrarResponse;
import altaqias.ragatanga.to.QuestUltimasResponse;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean(name="ViagensBean")
public class ViagensBean {

	@Getter @Setter
	private List<Quest> viagens;

	@Getter @Setter
	private String erro;
	
	@ManagedProperty(value="#{SessaoBean}")
	@Getter @Setter
	private SessaoBean sessao;

	@PostConstruct
	public void init() {
		try{
			QuestUltimasResponse response = QuestController.ultimas();
			if(response != null){
				if(response.getMensagem().getCodigo().equals(MensagemTO.CODIGO_SUCESSO)){
					this.viagens = response.getQuests();
					if(this.viagens == null || this.viagens.isEmpty()){
						this.erro = "Nao existem viagens disponiveis.";
					}
				}else{
					this.erro = response.getMensagem().getMensagem();
				}
			}else{
				this.erro = "Erro no servidor. Nao foi possivel listar as viagens disponiveis.";
			}
		}catch(Exception e){
			e.printStackTrace();
			this.erro = "Erro no servidor. Nao foi possivel listar as viagens disponiveis.";
		}
	}
	
	public void irTambem(Integer idQuest){
		try{
			QuestEntrarResponse response = QuestController.entrar(this.sessao.getCliente().getId(), idQuest);
			if(response != null){
				if(response.getMensagem().getCodigo().equals(MensagemTO.CODIGO_SUCESSO)){
					FacesUtils.sucessGrow("Uhuu!!! Sua inscricao foi solicitada com sucesso, existe uma grande possibilidade de voce ir junto nessa. Aguarde a aprovacao do admin desda viagem.");
					QuestUltimasResponse quResponse = QuestController.ultimas();
					this.viagens = quResponse.getQuests();
				}else{
					FacesUtils.alertGrow(response.getMensagem().getMensagem());
				}
			}else{
				FacesUtils.alertGrow("Erro no servidor. Nao foi possivel se inscrever nessa viagem.");
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesUtils.alertGrow("Erro no servidor. Nao foi possivel listar as viagens disponiveis.");
		}
	}
	
	public boolean inscricaoJaRealizada(){
		for(Quest q: this.viagens){
			for(Inscricao i: q.getInscricoes()){
				if(i.getCliente().getId() == this.sessao.getCliente().getId()){
					return true;
				}
			}
		}
		return false;
	}

}
