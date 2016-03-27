package saleorder.facesconverters;

import com.isd.myjaxrs.entity.Item;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import item.beans.ItemBackingBean;
import item.beans.ItemClientBean;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ItemConverter implements Converter {
@Inject
private ItemBackingBean bb;
@Inject
private ItemClientBean cb;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
//                System.out.println("saleorder.facesconverters.ItemConverter.getAsObject()");
                bb.setId(Long.parseLong(value));
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
//        System.out.println("saleorder.facesconverters.ItemConverter.getAsString()");
        if (value != null) {
            return String.valueOf(((Item) value).getId());
        } else {
            return null;
        }
    }

}