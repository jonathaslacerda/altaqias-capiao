package altaqias.ragatanga.capiao.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import altaqias.ragatanga.capiao.utils.FormatUtil;

@FacesConverter(value="DinheiroConverter")
public class DinheiroConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		String normalizado = FormatUtil.normalizarDecimal(arg2);
		if(!normalizado.isEmpty()){
			return Float.parseFloat(normalizado);
		}
		return "";
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2 instanceof Double){
			Double resultado = (Double)arg2;
			return FormatUtil.formatarDinheiro(String.valueOf(resultado));
		}
		return String.valueOf(arg2);
	}

}
