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
import beans.sys.RoleEJB;
import entities.sys.SysRole;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class RoleConverter implements Converter {

    @Inject
    private RoleEJB ejb;

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
            return String.valueOf(((SysRole) value).getRole_id());
        } else {
            return null;
        }
    }

    public List<SysRole> completeRole(String query) {
        List<SysRole> allItems = ejb.findAll();
        List<SysRole> filteredItems = new ArrayList<>();
        for (SysRole item : allItems) {
            if (item.getRoleName().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

}
