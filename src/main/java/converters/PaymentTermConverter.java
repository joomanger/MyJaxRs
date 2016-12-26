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
import beans.payment.PaymentCBean;
import entities.payment.PaymentTerm;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class PaymentTermConverter implements Converter {

    @Inject
    private PaymentCBean client;

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
            return String.valueOf(((PaymentTerm) value).getPayment_id());
        } else {
            return null;
        }
    }

    public List<PaymentTerm> completeItem(String query) {
        List<PaymentTerm> allItems = client.findAll();
        List<PaymentTerm> filteredItems = new ArrayList<>();

        for (PaymentTerm pt : allItems) {
            try {
                if (pt.getCondition().toLowerCase().contains(query.toLowerCase())) {
                    filteredItems.add(pt);
                }
            } catch (NullPointerException ex) {
                return null;
            }
        }
        return filteredItems;

    }

}
