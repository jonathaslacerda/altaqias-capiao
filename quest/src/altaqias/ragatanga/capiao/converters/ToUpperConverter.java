package altaqias.ragatanga.capiao.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("ToUpperConverter")
public class ToUpperConverter implements Converter {

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return (String) value;
    }

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return (value != null) ? value.toUpperCase() : null;
    }

}