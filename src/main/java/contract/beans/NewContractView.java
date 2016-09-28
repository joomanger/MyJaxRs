package contract.beans;

import contract.entities.Contract;
import customer.entities.Customer;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewContractView extends AbstractView<Contract> {

    private final Contract contract = new Contract();

    //private Party newParty = new Party();
    private Customer customer;
    private String partyRole;

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(contract);
    }

//    public Party getNewParty() {
//        return newParty;
//    }
//
//    public void setNewParty(Party newParty) {
//        this.newParty = newParty;
//    }
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
