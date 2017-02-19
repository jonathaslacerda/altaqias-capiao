package altaqias.ragatanga.capiao.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

@FacesValidator("CpfValidator")
public class CpfValidator implements Validator {

	public void validate(FacesContext arg0, UIComponent arg1, Object cpf)throws ValidatorException {
		try{
			CPFValidator validator = new CPFValidator();
			validator.assertValid(cpf.toString());
		}catch(InvalidStateException e){
			FacesMessage msg = new FacesMessage("", "CPF inválido.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}

}
