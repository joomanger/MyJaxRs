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
import service.Secure;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class OpenContractView extends AbstractView<Contract> {

    @Inject
    private ContractCBean client;
    @Inject
    private SessionActions sa;
    @Inject
    private LookupCBean clientLookup;
    @Inject
    private FindContractSession fs;

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
        super.setEntity(client.find(fs.getContract_id()));
        contractRoles = clientLookup.findByName("CONTRACT ROLES");
        contractGroup = clientLookup.findByName("CONTRACT GROUPS");
        contractPurpose = clientLookup.findByName("CONTRACT PURPOSES");
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
