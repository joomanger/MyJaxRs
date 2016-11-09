package converters;

import contract.beans.ContractCBean;
import contract.entities.Contract;
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
import so.saleorder.flows.CreateOrderFlow;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ContractConverter implements Converter {

    @Inject
    private ContractCBean client;
//    @Inject
//    private SessionActions sa;
    @Inject
    private CreateOrderFlow of;

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
            return String.valueOf(((Contract) value).getContract_id());
        } else {
            return null;
        }
    }

    public List<Contract> completeItem(String query) {
        List<Contract> allItems = client.findAll();
        List<Contract> filteredItems = new ArrayList<>();
        allItems.stream().filter((item) -> item.getContractNumber().toLowerCase()
                .contains(query.toLowerCase())).forEach((Contract item) -> {
            filteredItems.add(item);
        });
        return filteredItems;

    }

    public List<Contract> completeItemByINV(String query) {
        if (of.getOrder().getInv_customer() != null) {
            List<Contract> allItems = client.findByINV(of.getOrder().getInv_customer().getCustomer_id());
            List<Contract> filteredItems = new ArrayList<>();
            allItems.stream().filter((item) -> item.getContractNumber().toLowerCase().contains(query.toLowerCase())).forEach((Contract item) -> {
                filteredItems.add(item);
            });
            return filteredItems;
        } else {
            return null;
        }
    }

}
