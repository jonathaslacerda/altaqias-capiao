package altaqias.ragatanga.capiao.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import altaqias.ragatanga.apiclient.control.LocalizacaoController;
import altaqias.ragatanga.apiclient.control.QuestController;
import altaqias.ragatanga.capiao.utils.FacesUtils;
import altaqias.ragatanga.model.Cidade;
import altaqias.ragatanga.model.Destino;
import altaqias.ragatanga.model.Quest;
import altaqias.ragatanga.to.CidadesPorEstadoResponse;
import altaqias.ragatanga.to.MensagemTO;
import altaqias.ragatanga.to.QuestCriarResponse;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean(name="ViajarBean")
public class ViajarBean {
	
	@Getter @Setter
	private Quest viagem;
	
	@Getter @Setter
	private List<Cidade> cidades;
	
	@Getter @Setter
	private Destino destino;
	
	@ManagedProperty(value="#{SessaoBean}")
	@Getter @Setter
	private SessaoBean sessao;
	
	@PostConstruct
	private void init(){
		this.destino = new Destino();
		this.viagem = new Quest();
		CidadesPorEstadoResponse response = LocalizacaoController.cidades();
		this.cidades = response.getCidades();
	}
	
	public void salvar(){
		this.viagem.setDestino(this.destino);
		this.viagem.setCriador(this.sessao.getCliente());
		QuestCriarResponse response = QuestController.criar(this.viagem);
		if(response != null){
			if(response.getMensagem().getCodigo().equals(MensagemTO.CODIGO_SUCESSO)){
				FacesUtils.sucessGrow("O primeiro passo foi dado, agora é só esperar as inscrições e começar a economizar. Boa Sorte!");
			}else{
				FacesUtils.alertGrow(response.getMensagem().getMensagem());
			}
		}else{
			FacesUtils.alertGrow("Opss... Ocorreu um erro ao criar sua viagem. Tenta novamente daqui a pouco.");
		}
	}
	
	public List<SelectItem> cidades(){
		List<SelectItem> itens = new ArrayList<>();
		itens.add(new SelectItem(null, ""));
		for(Cidade cidade: this.cidades){
			itens.add(new SelectItem(cidade, cidade.getNome() + " - " + cidade.getEstado().getNome() + " - " + cidade.getEstado().getPais().getNome()));
		}
		return itens;
	}

}
