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
import sys.beans.MenuEJB;
import sys.entities.Menu;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class MenuConverter implements Converter {

    @Inject
    private MenuEJB ejb;

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
            return String.valueOf(((Menu) value).getMenu_id());
        } else {
            return null;
        }
    }

    public List<Menu> completeMenu(String query) {
        List<Menu> allItems = ejb.findAll();
        List<Menu> filteredItems = new ArrayList<>();
        for (Menu item : allItems) {
            if (item.getMenuName().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
}
