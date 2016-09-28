package contract.beans;

import contract.entities.Contract;
import contract.entities.Party;
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
    private ContractPartyEJB partyEJB;
    
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
    
    public void deletePartyNV() {
        nv.getEntity().getParties().removeAll(nv.getSelectedEntityLines());
    }
    
    public void addPartyNV() {
        Party party = nv.getNewParty();
        String validation = partyEJB.validateMyEntity(party);
        if (validation.equals(partyEJB.SUCCESSFUL)) {
            nv.getEntity().addParty(party);
            nv.setNewParty(new Party());
        } else {
            partyEJB.sendMessage(validation, null);
        }
    }
    
}
