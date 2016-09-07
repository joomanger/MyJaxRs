package customer.beans;

import customer.entities.Customer;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class CustomerEJB extends AbstractEJB<Customer> {

    @Inject
    private EntityManager em;

//    @Inject
//    private SessionActions sa;

    public CustomerEJB() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//    public Lookup findByName(String name) {
//        TypedQuery<Lookup> tq = em.createNamedQuery(Lookup.FIND_BY_NAME, Lookup.class).setParameter("p_name", name);
//        return tq.getSingleResult();
//    }
//
//    // Мэппинг прописал в orm.xml, так как аннотации не воспринимает!!!
//    public List<LookupItem> findLookupItemVL(Long p_lookup_id) {
//        List<LookupItem> a = em.createNativeQuery("select c.lookupitem_id, c.activestatus,c.valuez,"
//                + "t.meaning, t.description  from LookupItem c,LookupItemTL t "
//                + "where c.lookup_id=?1 and c.lookupitem_id=t.lookupitem_id and "
//                + "t.language=?2 order by c.valuez", "LookupItemVLMapping").setParameter(1, p_lookup_id).
//                setParameter(2, sa.getLanguage()).getResultList();
//        return a;
//    }

}
