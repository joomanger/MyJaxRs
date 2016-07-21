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
import lookup.beans.LookupCBean;
import lookup.beans.LookupItemCBean;
import lookup.entities.Lookup;
import lookup.entities.LookupItem;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class UomConverter implements Converter {

    @Inject
    private LookupCBean client;
    @Inject
    private LookupItemCBean clientItem;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return clientItem.find(Long.parseLong(value));

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
            return String.valueOf(((LookupItem) value).getLookupItem_id());
        } else {
            return null;
        }
    }

    public List<String> completeItem(String query) {
        Lookup l = client.findByName("UOM");
        List<LookupItem> allItems = l.getLookupItems();
        List<String> filteredItems = new ArrayList<>();

        allItems.stream().filter((item) -> (item.getValuez().toLowerCase().contains(query.toLowerCase()))).forEach((item) -> {
            filteredItems.add(item.getValuez());
        });
        return filteredItems;
    }
}
