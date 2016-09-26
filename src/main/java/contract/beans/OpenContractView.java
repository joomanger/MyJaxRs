package contract.beans;

import contract.entities.Contract;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenContractView extends AbstractView<Contract> {

    @Inject
    private ContractCBean client;
    @Inject
    private FindContractSession fs;

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(client.find(fs.getContract_id()));
    }

}
