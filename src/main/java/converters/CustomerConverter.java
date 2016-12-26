package converters;

import beans.customer.CustomerCBean;
import entities.customer.Customer;
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
import flows.so.saleorder.CreateOrderFlow;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class CustomerConverter implements Converter {

    @Inject
    private CustomerCBean client;
    @Inject
    private CreateOrderFlow of;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return client.find(Long.parseLong(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Conversion Error", "Not a valid theme."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((Customer) value).getCustomer_id());
        } else {
            return null;
        }
    }

    public List<Customer> completeItem(String query) {
        List<Customer> all = client.findAll();
        List<Customer> filtered = new ArrayList<>();
        all.stream().filter((item) -> item.getName().toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
            filtered.add(item);
        });
        return filtered;
    }

    public List<Customer> completeSHP(String query) {
        if (of.getOrder().getCustomer() != null) {
            List<Customer> all = client.getRelCustomersByCustomerID(of.getOrder().getCustomer().getCustomer_id(), Boolean.TRUE, Boolean.FALSE);
            List<Customer> filtered = new ArrayList<>();
            all.stream().filter((item) -> item.getName().toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
                filtered.add(item);
            });
            return filtered;
        } else {
            return null;
        }
    }

    public List<Customer> completeINV(String query) {
        if (of.getOrder().getCustomer() != null) {
            List<Customer> all = client.getRelCustomersByCustomerID(of.getOrder().getCustomer().getCustomer_id(), Boolean.FALSE, Boolean.TRUE);
            List<Customer> filtered = new ArrayList<>();
            all.stream().filter((item) -> item.getName().toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
                filtered.add(item);
            });
            return filtered;
        } else {
            return null;
        }
    }

}
