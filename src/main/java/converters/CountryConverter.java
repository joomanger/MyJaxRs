package converters;

import beans.customer.CountryCBean;
import entities.customer.Country;
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
import service.SessionActions;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class CountryConverter implements Converter {

    @Inject
    private CountryCBean client;
    @Inject
    private SessionActions sa;

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
            return String.valueOf(((Country) value).getCountry_id());
        } else {
            return null;
        }
    }

    public List<Country> completeItem(String query) {
        List<Country> allItems = client.findAll();
        List<Country> filteredItems = new ArrayList<>();

        allItems.stream().filter((item) -> item.getTranslateObject(sa.getLanguage()).getName().toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
            filteredItems.add(item);
        });
        return filteredItems;
    }

}
