package converters;

import item.beans.ItemCBean;
import item.entities.Item;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ItemConverter implements Converter {

    @Inject
    private ItemCBean client;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return client.find(Long.parseLong(value));

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
            return String.valueOf(((Item) value).getItem_id());
        } else {
            return null;
        }
    }

    public List<Item> completeItem(String query) {
        List<Item> allItems = client.findAll();
        List<Item> filteredItems = new ArrayList<>();

        allItems.stream().filter((item) -> item.getName().toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
            filteredItems.add(item);
        });
        return filteredItems;
    }

}
