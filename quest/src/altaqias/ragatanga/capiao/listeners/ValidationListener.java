package altaqias.ragatanga.capiao.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import org.apache.commons.lang3.StringUtils;


public class ValidationListener implements SystemEventListener {
	private static final String CSS_CLASS_NAME = "has-error-validation";

	/* Percorrer toda a arvore de componentes do JSF, encontrando os inputs
	 * que estejam com erro de valida��o, a alterando a sua classe CSS para "invalid-field".
	 * 
	 *  A classe CSS ser� acrescentada ao componente caso outras classes j� existam, sem excluir
	 *  as antigas.*/
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		
		UIViewRoot uiViewRoot = (UIViewRoot) event.getSource();
		List<UIInput> inputs = recuperarTodosInputs(uiViewRoot.getChildren());
		
		/* Primeiro ser� necess�rio retirar a nossa classe CSS de erro de qualquer input
		 * que j� a possuir. Necess�rio pois se o componente tiver sido detectado com erro
		 * anteriormente e tiver sido corrigido, ele n�o precisa mais estar destacado. */
		for (UIInput uiInput : inputs) {
			removerCss(uiInput);
		}
		
		/* Percorre os inputs novamente para atribuir a nossa classe CSS de erro
		 * aqueles que estiverem inv�lidos. */
		for (UIInput uiInput : inputs) {
			if (!uiInput.isValid() || possuiMensagensDeErro(uiInput)) { //somente invalidos ou que possuam mensagens de erro
				acrescentarCss(uiInput);
			}
		}
	}

	public boolean isListenerForSource(Object source) {
		if (source instanceof UIViewRoot) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Percorrer recursivamente a arvore de componentes e retorna
	 * todos os componentes que s�o "UIInput".
	 */
	private List<UIInput> recuperarTodosInputs(List<UIComponent> components) {
		List<UIInput> retorno = new ArrayList<>();

        for (UIComponent c : components) {
            // se tem filhos, faz recursao
            if (c.getChildCount() != 0) {
                retorno.addAll(recuperarTodosInputs(c.getChildren()));
            }
            if (c instanceof UIInput) {
            	retorno.add((UIInput) c);
            }
        }
        return retorno;
	}
	
	/*
	 * Recupera o atributo "styleClass" do UIInput passado por parametro
	 * e remove o nosso CSS de destaque da lista de classes CSS existentes.
	 * 
	 * Exemplo:
	 * <h:inputText styleClass="campos-texto invalid-field"/>
	 * 
	 * vai ficar assim:
	 * <h:inputText styleClass="campos-texto"/>
	 * 
	 * A l�gica desse m�todo leva em conta que as classes CSS podem ser separadas
	 * por espa�o em branco ou virgulas, como manda a especifica��o JSF.
	 */
	private void removerCss(UIInput input) {
		//String com classes CSS separadas por espa�o em branco, ou virgula
		String styleClassOriginal = (String) input.getAttributes().get("styleClass");
		
		if (StringUtils.isNotBlank(styleClassOriginal)) {
			List<String> classes = new ArrayList<>(Arrays.asList(styleClassOriginal.split("[, ]+"))); //separar classes em uma lista
			classes.remove(CSS_CLASS_NAME); //tenta remover a nossa classe CSS se existir
			String styleClassModificado = StringUtils.join(classes, " ").trim();  //concatena novamente todas as classes CSS em uma unica String
			input.getAttributes().put("styleClass", styleClassModificado); //seta novamente no input
		}
	}
	
	/*
	 * Concatena o nosso CSS de erro ao atributo
	 * "styleClass" do UIInput passado como parametro.
	 * 
	 */
	private void acrescentarCss(UIInput input) {
		String styleClassOriginal = (String) input.getAttributes().get("styleClass");
		
		if (StringUtils.isNotBlank(styleClassOriginal)) {
			List<String> classes = new ArrayList<>(Arrays.asList(styleClassOriginal.split("[, ]+"))); //separar classes em uma lista
			classes.add(CSS_CLASS_NAME); //adiciona a nossa classe CSS
			String styleClassModificado = StringUtils.join(classes, " ").trim();  //concatena novamente todas as classes CSS em uma unica String
			input.getAttributes().put("styleClass", styleClassModificado); //seta no input
			
		} else {
			//atributo styleClass j� estava vazio, n�o precisa concatenar, somente 
			//adicionar o nosso
			input.getAttributes().put("styleClass", CSS_CLASS_NAME);
		}
	}
	
	/* Verifica se o input possui alguma mensagem de erro adicionada, 
	 * ou seja, um FacesMessage do tipo ERROR.
	 * Esse teste serve para garantir que mesmo valida��es feitas
	 * "na m�o", na fase "INVOKE APPLICATION", destaque
	 * o componente como sendo invalido. */
	private boolean possuiMensagensDeErro(UIInput input) {
		String clientId = input.getId();
		List<FacesMessage> messages = FacesContext.getCurrentInstance().getMessageList(clientId);
		for (FacesMessage message : messages) {
			if (FacesMessage.SEVERITY_ERROR.equals(message.getSeverity())) {
				return true;
			}
		}
		return false;
	}
}
