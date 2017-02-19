package altaqias.ragatanga.capiao.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import altaqias.ragatanga.capiao.utils.FormatUtil;

@FacesConverter(value="TelefoneConverter")
public class TelefoneConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return FormatUtil.normalizar(arg2);
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String fone = (String) arg2;
		if(fone.equalsIgnoreCase("")){
			return "";
		}
		if(fone.charAt(0) == '0'){
			fone = fone.substring(1);
		}
		return FormatUtil.formatarTelefone(fone);
	}

}
