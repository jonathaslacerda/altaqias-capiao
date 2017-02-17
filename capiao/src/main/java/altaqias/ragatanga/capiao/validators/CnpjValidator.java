package altaqias.ragatanga.capiao.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.InvalidStateException;

@FacesValidator("CnpjValidator")
public class CnpjValidator implements Validator {

	public void validate(FacesContext arg0, UIComponent arg1, Object cnpj)throws ValidatorException {
		try{
			CNPJValidator validator = new CNPJValidator();
			validator.assertValid(cnpj.toString());
		}catch(InvalidStateException e){
			FacesMessage msg = new FacesMessage("", "CNPJ inválido.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}


}
