package lookup.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lookup.entities.Lookup;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class LookupEJB extends AbstractEJB<Lookup> {

    @Inject
    private EntityManager em;

    public LookupEJB() {
        super(Lookup.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
