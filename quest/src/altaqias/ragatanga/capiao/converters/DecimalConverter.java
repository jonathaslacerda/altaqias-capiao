package altaqias.ragatanga.capiao.converters;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import altaqias.ragatanga.capiao.utils.FormatUtil;

@FacesConverter(value="DecimalConverter")
public class DecimalConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if(arg2.equalsIgnoreCase("")){
			return new BigDecimal("0.00");
		}else{
			String normalizado = FormatUtil.normalizarDecimal(arg2);
			BigDecimal decimal = new BigDecimal(normalizado);
			return decimal;
		}
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		BigDecimal decimal = (BigDecimal) arg2;
		return decimal.toPlainString();
	}

}
