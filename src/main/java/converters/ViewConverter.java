package converters;

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
import sys.beans.ViewEJB;
import sys.entities.View;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ViewConverter implements Converter {

    @Inject
    private ViewEJB ejb;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return ejb.find(Long.parseLong(value));
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
            return String.valueOf(((View) value).getView_id());
        } else {
            return null;
        }
    }

    public List<View> completeView(String query) {
        List<View> allItems = ejb.findAll();
        List<View> filteredItems = new ArrayList<>();
        for (View item : allItems) {
            if (item.getViewName().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

}
