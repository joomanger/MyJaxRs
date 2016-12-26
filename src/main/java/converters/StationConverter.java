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
import beans.rw.RWStationCBean;
import entities.rw.RWStation;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class StationConverter implements Converter {

    @Inject
    private RWStationCBean client;
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
            return String.valueOf(((RWStation) value).getRws_code());
        } else {
            return null;
        }
    }

    public List<RWStation> completeItem(String query) {
        List<RWStation> allItems = client.findAll();
        List<RWStation> filteredItems = new ArrayList<>();

        for (RWStation rw : allItems) {
            try {
                if (rw.getTranslateObject(sa.getLanguage()).getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredItems.add(rw);
                }
            } catch (NullPointerException ex) {
                return filteredItems;
            }
        }
        return filteredItems;

    }

}
