package altaqias.ragatanga.capiao.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import altaqias.ragatanga.capiao.utils.FormatUtil;

@FacesValidator("MaiorQueZeroValidator")
public class MaiorQueZeroValidator implements Validator {

	public void validate(FacesContext arg0, UIComponent arg1, Object param)throws ValidatorException {
		String valor = FormatUtil.normalizar(param.toString());
		try{
			int numero = Integer.parseInt(valor);
			if(numero < 1){
				FacesMessage fmsg = new FacesMessage("", "Deve ser maior que zero");
				fmsg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(fmsg);
			}
		}catch(NumberFormatException e){
			FacesMessage fmsg = new FacesMessage("", "Apenas números");
			fmsg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(fmsg);
		}

	}
}
