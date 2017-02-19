package altaqias.ragatanga.capiao.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import altaqias.ragatanga.apiclient.control.QuestController;
import altaqias.ragatanga.model.Quest;
import lombok.Getter;
import lombok.Setter;

@ViewScoped
@ManagedBean(name="ViagensBean")
public class ViagensBean {

	@Getter @Setter
	List<Quest> quests;
	
	@PostConstruct
	public void init() {
//		this.quests = this.carregarUltimasQuests();
		this.quests = quests = new ArrayList<Quest>();
	}

	@SuppressWarnings("unchecked")
	private List<Quest> carregarUltimasQuests() {
		List<Quest> quests = (List<Quest>) QuestController.ultimas();
		if(quests == null){
			quests = new ArrayList<Quest>();
		}
		return quests;
	}
}
