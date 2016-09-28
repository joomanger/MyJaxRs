package contract.beans;

import contract.entities.Contract;
import contract.entities.Party;
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

    private Party newParty = new Party();

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(contract);
    }

    public Party getNewParty() {
        return newParty;
    }

    public void setNewParty(Party newParty) {
        this.newParty = newParty;
    }

}
