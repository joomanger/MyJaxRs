package contract.beans;

import contract.entities.Contract;
import contract.entities.Party;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    public List<Contract> findAll() {
        List<Contract> l = super.findAll();
        Collections.sort(l, new Comparator<Contract>() {
            @Override
            public int compare(Contract l1, Contract l2) {
                return l1.getContractNumber().compareTo(l2.getContractNumber());
            }
        });
        return l;
    }

    @Override
    public void deleteSelectedEntities() {
        super.deleteSelectedEntities();
        fv.updateLazyDataModel();
    }

    public void deletePartyNV() {
        nv.getEntity().getParties().removeAll(nv.getSelectedEntityLines());
    }

    public void deletePartyOV() {
        ov.getEntity().getParties().removeAll(ov.getSelectedEntityLines());
        changeEntity();
    }

    public void addPartyOV() {
        Party party = new Party();
        party.setCustomer(ov.getCustomer());
        party.setRole(ov.getPartyRole());
        String validation = partyEJB.validateMyEntity(party);
        if (validation.equals(partyEJB.SUCCESSFUL)) {
            party.setContract(ov.getEntity());
            String result = partyEJB.create(party);
            if (result.equals(partyEJB.SUCCESSFUL)) {
                partyEJB.sendMessage(result, "Сторона добавлена успешно");
                ov.setCustomer(null);
                ov.setPartyRole(null);
            } else {
                partyEJB.sendMessage(result, null);
            }
        } else {
            partyEJB.sendMessage(validation, null);
        }
    }

    public void addPartyNV() {
        Party party = new Party();
        party.setCustomer(nv.getCustomer());
        party.setRole(nv.getPartyRole());

        String validation = partyEJB.validateMyEntity(party);
        if (validation.equals(partyEJB.SUCCESSFUL)) {
            nv.getEntity().addParty(party);
            nv.setCustomer(null);
            nv.setPartyRole(null);
            //nv.setNewParty(new Party());
        } else {
            partyEJB.sendMessage(validation, null);
        }
    }

}
