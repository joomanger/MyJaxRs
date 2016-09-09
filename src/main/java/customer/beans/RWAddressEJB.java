package customer.beans;

import customer.entities.RWAddress;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class RWAddressEJB extends AbstractEJB<RWAddress> {

    @Inject
    private EntityManager em;

    public RWAddressEJB() {
        super(RWAddress.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
