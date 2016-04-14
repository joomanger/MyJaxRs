package converters;

import config.beans.FindParameterSession;
import config.beans.ParameterClientBean;
import config.entity.ParameterConfiguration;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ParameterConverter implements Converter {

    @Inject
    private FindParameterSession ps;

    @Inject
    private ParameterClientBean cb;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                ps.setParamID(Long.parseLong(value));
                return cb.getItem();

            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((ParameterConfiguration) value).getParameter_id());
        } else {
            return null;
        }
    }

    public List<ParameterConfiguration> completeParameter(String query) {
        ParameterConfiguration[] allItems = cb.getItems();
        List<ParameterConfiguration> filteredItems = new ArrayList<>();

        for (ParameterConfiguration item : allItems) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item);
            }
        }

        return filteredItems;
    }

    public List<String> completeAttrParameter(String query) {
        List<String> allItems = cb.getAttrColumn();
        List<String> filteredItems = new ArrayList<>();

        for (String item : allItems) {
            if (item.toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

}