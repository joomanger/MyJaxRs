package contract.beans;

import contract.entities.Contract;
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

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(contract);
    }

}
