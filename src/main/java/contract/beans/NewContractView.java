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

    private final Contract contract = new Contract();
    // Поля для создания нового Отношения
    private Customer customer;
    private String partyRole;
    //Используемые справочники
    private Lookup contractRoles;
    private Lookup contractGroup;
    private Lookup contractPurpose;

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(contract);
        contractRoles = client.findByName("Contract roles");
        contractGroup = client.findByName("Contract groups");
        contractPurpose = client.findByName("Contract purposes");
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

    // Возвращаем из справочника TL-объект значения 
    public LookupItemTL getContractRolesTL(String value) {
        try {
            return contractRoles.getTranslateObject(value, sa.getLanguage());
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public LookupItemTL getContractGroupTL(String value) {
        try {
            return contractGroup.getTranslateObject(value, sa.getLanguage());
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public LookupItemTL getContractPurposeTL(String value) {
        try {
            return contractPurpose.getTranslateObject(value, sa.getLanguage());
        } catch (NullPointerException ex) {
            return null;
        }
    }

}
