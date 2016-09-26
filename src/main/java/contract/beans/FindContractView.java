package contract.beans;

import contract.entities.Contract;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class FindContractView extends AbstractView<Contract> {

    @Inject
    private ContractCBean client;
    private LazyDataModel<Contract> lazyModel;

    @PostConstruct
    @Override
    protected void init() {
        try {
            lazyModel = new LazyContractDataModel(client.findAll(), Contract.class);
        } catch (IllegalAccessException | InstantiationException ex) {
            System.out.println(ex);
        }
    }

    public LazyDataModel<Contract> getLazyModel() {
        return lazyModel;
    }

}
