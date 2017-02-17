package altaqias.ragatanga.capiao.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

@FacesValidator("NumeroValidator")
public class NumeroValidator implements Validator {

	public void validate(FacesContext arg0, UIComponent arg1, Object valor)throws ValidatorException {
		if(!StringUtils.isNumeric(valor.toString())){
			FacesMessage msg = new FacesMessage("", "Apenas números.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
