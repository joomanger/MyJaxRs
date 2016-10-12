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
import sys.beans.UserCBean;
import sys.entities.SysUser;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class UserConverter implements Converter {

    @Inject
    private UserCBean client;

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
            return String.valueOf(((SysUser) value).getUser_id());
        } else {
            return null;
        }
    }

    public List<SysUser> completeItem(String query) {
        List<SysUser> allItems = client.findAll();
        List<SysUser> filteredItems = new ArrayList<>();

        allItems.stream().filter((item) -> item.getFullName().toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
            filteredItems.add(item);
        });
        return filteredItems;
    }

    public List<SysUser> completeTrader(String query) {
        List<SysUser> allItems = client.findAll();
        List<SysUser> filteredItems = new ArrayList<>();

        allItems.stream().filter((item) -> item.getFullName().toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
            if (item.getTrader()) {
                filteredItems.add(item);
            }
        });
        return filteredItems;
    }

}
