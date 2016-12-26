package beans.contract;

import entities.contract.Party;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class ContractPartyEJB extends AbstractEJB<Party> {

    @Inject
    private EntityManager em;

    public ContractPartyEJB() {
        super(Party.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
