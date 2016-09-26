package contract.beans;

import contract.entities.Contract;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class ContractEJB extends AbstractEJB<Contract> {

    @Inject
    private EntityManager em;

    public ContractEJB() {
        super(Contract.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
