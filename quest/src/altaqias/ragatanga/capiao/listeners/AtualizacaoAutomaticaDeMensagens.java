package altaqias.ragatanga.capiao.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import altaqias.ragatanga.capiao.utils.FacesUtils;

@SuppressWarnings("serial")
public class AtualizacaoAutomaticaDeMensagens implements PhaseListener {

	public void afterPhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void beforePhase(PhaseEvent arg0) {
		FacesUtils.atualizarComponente("mensagens");
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
