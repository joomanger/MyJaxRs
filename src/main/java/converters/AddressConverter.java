package converters;

import beans.customer.AddressEJB;
import entities.customer.Address;
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
import flows.so.saleorder.CreateOrderFlow;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class AddressConverter implements Converter {

//    @Inject
//    private CustomerCBean client;
    @Inject
    private AddressEJB addrEJB;

    @Inject
    private SessionActions sa;
    @Inject
    private CreateOrderFlow of;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return addrEJB.find(Long.parseLong(value));//client.find(value);
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
            return String.valueOf(((Address) value).getAddress_id());
        } else {
            return null;
        }
    }

    public List<Address> completeAddressSHP(String query) {
        if (of.getOrder().getShp_customer() != null) {
            List<Address> allItems = of.getOrder().getShp_customer().getAddresses();
            List<Address> filteredItems = new ArrayList<>();
            allItems.stream().filter((item) -> (item.getCountry().getTranslateObject(sa.getLanguage()).getName()
                    + " " + item.getCity() + " " + item.getFullAddress() + " " + item.getRegion())
                    .toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
                if (item.getShip_to() && item.getActiveStatus()) {
                    filteredItems.add(item);
                }
            });
            return filteredItems;
        } else {
            return null;
        }

    }

    public List<Address> completeAddressINV(String query) {
        if (of.getOrder().getInv_customer() != null) {
            List<Address> allItems = of.getOrder().getInv_customer().getAddresses();
            List<Address> filteredItems = new ArrayList<>();
            allItems.stream().filter((item) -> (item.getCountry().getTranslateObject(sa.getLanguage()).getName()
                    + " " + item.getCity() + " " + item.getFullAddress() + " " + item.getRegion())
                    .toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
                if (item.getBill_to() && item.getActiveStatus()) {
                    filteredItems.add(item);
                }
            });
            return filteredItems;
        } else {
            return null;
        }
    }

}
