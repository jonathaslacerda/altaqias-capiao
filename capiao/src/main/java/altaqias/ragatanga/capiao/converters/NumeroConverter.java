package altaqias.ragatanga.capiao.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="NumeroConverter")
public class NumeroConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
		int valor;
		try{
			valor = Integer.parseInt(arg2);
		}catch(NumberFormatException nfe){
			FacesMessage msg = new FacesMessage("", "Apenas números.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}
		return valor;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
			return String.valueOf(arg2);
	}

}
