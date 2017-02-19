package altaqias.ragatanga.capiao.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import altaqias.ragatanga.capiao.utils.FormatUtil;

@FacesConverter(value="DataConverter")
public class DataConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = (Date)formatter.parse(arg2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String dataStr = FormatUtil.formatarData((Date)arg2, FormatUtil.MASK_DATE);
		return dataStr;
	}

}
