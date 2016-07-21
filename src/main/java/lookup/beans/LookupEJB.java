package lookup.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    public Lookup findByName(String name) {
        TypedQuery<Lookup> tq = em.createNamedQuery(Lookup.FIND_BY_NAME, Lookup.class).setParameter("p_name", name);
        return tq.getSingleResult();
    }
}
