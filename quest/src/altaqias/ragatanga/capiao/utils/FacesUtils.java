package altaqias.ragatanga.capiao.utils;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public abstract class FacesUtils {

	public static void adicionaMensagemDeErro(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	public static void adicionaMensagemDeErro(String id, String msg) {
		FacesContext.getCurrentInstance().addMessage(id, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	public static void adicionaMensagemDeInformacao(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public static void adicionaMensagemDeAtencao(String msg){
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
	}

	public static void atualizarComponente(String id){
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(id);
	}

	public static void cleanSubmittedValues(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			EditableValueHolder evh = (EditableValueHolder) component;
			evh.setSubmittedValue(null);
			evh.setValue(null);
			evh.setLocalValueSet(false);
			evh.setValid(true);
		}
		if(component.getChildCount()>0){
			for (UIComponent child : component.getChildren()) {
				cleanSubmittedValues(child);
			}
		}
	}

	public static List<FacesMessage> getMensagens() {
		return FacesContext.getCurrentInstance().getMessageList();
	}

	public static void sucessGrow(String msg) {
		RequestContext.getCurrentInstance().execute("grow('"+msg+"','gritter-success')");
	}

	public static void alertGrow(String msg) {
		RequestContext.getCurrentInstance().execute("grow('"+msg+"','gritter-warning')");
	}

	public static String realPath() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ex = fc.getExternalContext();
		return ex.getRealPath("");
	}

}
