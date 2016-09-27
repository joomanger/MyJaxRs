package contract.beans;

import contract.entities.Contract;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ContractCBean extends AbstractClientBean<Contract> {

    @Inject
    private ContractEJB ejb;

    @Inject
    private FindContractView fv;
    @Inject
    private NewContractView nv;
    @Inject
    private OpenContractView ov;

    @Override
    protected AbstractEJB<Contract> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<Contract> getOpenView() {
        return ov;
    }

    @Override
    protected AbstractView<Contract> getFindView() {
        return fv;
    }

    @Override
    protected AbstractView<Contract> getNewView() {
        return nv;
    }
    
    @Override
    public void deleteSelectedEntities() {
        super.deleteSelectedEntities();
        fv.updateLazyDataModel();
    }

}
