package altaqias.ragatanga.capiao.converters;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import altaqias.ragatanga.capiao.utils.FormatUtil;

@FacesConverter(value="DinheiroBigDecimalConverter")
public class DinheiroBigDecimalConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		String normalizado = FormatUtil.normalizarDecimal(arg2);
		if(!normalizado.isEmpty()){
			return new BigDecimal(normalizado);
		}
		return new BigDecimal("0");
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return FormatUtil.formatarDinheiro(arg2.toString());
	}

}
