package altaqias.ragatanga.capiao.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import altaqias.ragatanga.capiao.utils.FormatUtil;

@FacesConverter(value="DocumentoConverter")
public class DocumentoConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return FormatUtil.normalizar(arg2);
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String documento = (String) arg2;
		if(documento.equalsIgnoreCase("")){
			return "";
		}
		return FormatUtil.formatarDocumento(documento);
	}

}
