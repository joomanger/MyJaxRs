package contract.beans;

import contract.entities.Contract;
import customer.entities.Customer;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.beans.LookupCBean;
import lookup.entities.Lookup;
import lookup.entities.LookupItemTL;
import service.AbstractView;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewContractView extends AbstractView<Contract> {

    @Inject
    private LookupCBean client;
    @Inject
    private SessionActions sa;
    
    private Lookup lookupContractRoles;
    private final Contract contract = new Contract();

    //private Party newParty = new Party();
    private Customer customer;
    private String partyRole;

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(contract);
        lookupContractRoles = client.findByName("Contract roles");
    }
    
    public LookupItemTL getTranslateObject(String p_value) {
        try {
            return lookupContractRoles.getTranslateObject(p_value, sa.getLanguage());
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPartyRole() {
        return partyRole;
    }

    public void setPartyRole(String partyRole) {
        this.partyRole = partyRole;
    }

}
