package lookup.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lookup.entities.LookupItemTL;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class LookupItemTLEJB extends AbstractEJB<LookupItemTL> {

    @Inject
    private EntityManager em;

    public LookupItemTLEJB() {
        super(LookupItemTL.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
