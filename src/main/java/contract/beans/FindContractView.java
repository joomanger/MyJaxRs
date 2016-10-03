package contract.beans;

import contract.entities.Contract;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.beans.LookupCBean;
import lookup.entities.Lookup;
import lookup.entities.LookupItemTL;
import org.primefaces.model.LazyDataModel;
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
public class FindContractView extends AbstractView<Contract> {

    @Inject
    private ContractCBean client;
    @Inject
    private LookupCBean lookupCBean;
    @Inject
    private SessionActions sa;
    
    private LazyDataModel<Contract> lazyModel;
    private Lookup contractGroup;

    @Override
    @PostConstruct
    protected void init() {
        updateLazyDataModel();
        contractGroup = lookupCBean.findByName("CONTRACT GROUPS");
    }

    public void updateLazyDataModel() {
        try {
            lazyModel = new LazyContractDataModel(client.findAll(), Contract.class);
        } catch (IllegalAccessException | InstantiationException ex) {
            System.out.println(ex);
        }
    }

    public LazyDataModel<Contract> getLazyModel() {
        return lazyModel;
    }

    public LookupItemTL getContractGroupTL(String value) {
        try {
            return contractGroup.getTranslateObject(value, sa.getLanguage());
        } catch (NullPointerException ex) {
            return null;
        }
    }

}
