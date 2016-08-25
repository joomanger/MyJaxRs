package lookup.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lookup.entities.Lookup;
import lookup.entities.LookupItemVL;
import service.AbstractEJB;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Stateless
public class LookupEJB extends AbstractEJB<Lookup> {

    @Inject
    private EntityManager em;

    @Inject
    private SessionActions sa;

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

    // Мэппинг прописал в orm.xml, так как аннотации не воспринимает!!!
    public List<LookupItemVL> findLookupItemVL(Long p_lookup_id) {
        List<LookupItemVL> a = em.createNativeQuery("select c.lookupitem_id, c.activestatus,c.valuez,"
                + "t.meaning, t.description  from LookupItem c,LookupItemTL t "
                + "where c.lookup_id=?1 and c.lookupitem_id=t.lookupitem_id and "
                + "t.language=?2 order by t.meaning", "LookupItemVLMapping").setParameter(1, p_lookup_id).
                setParameter(2, sa.getLanguage()).getResultList();
        return a;
    }

}
