package converters;

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
import rw.beans.RoadCBean;
import rw.entities.RWRoad;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class RoadConverter implements Converter {

    @Inject
    private RoadCBean client;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return client.find(value);
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
            return String.valueOf(((RWRoad) value).getRwr_code());
        } else {
            return null;
        }
    }

    public List<RWRoad> completeItem(String query) {
        List<RWRoad> allItems = client.findAll();
        List<RWRoad> filteredItems = new ArrayList<>();

        allItems.stream().filter((item) -> item.getFullName().toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
            filteredItems.add(item);
        });
        return filteredItems;
    }

}
