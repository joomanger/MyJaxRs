package customer.beans;

import customer.entities.Customer;
import java.util.List;
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

    public List<Customer> getRelCustomersByCustomerID(Long p_customer_id, Boolean p_ship_to, Boolean p_bill_to) {
       return em.createNativeQuery("select rc.*\n"
                + "from customer c,customer rc, relationship r\n"
                + " where c.customer_id=?1\n"
                + " and r.bill_to=?2 and r.ship_to=?3\n"
                + " and r.customer_id=c.customer_id\n"
                + " and rc.customer_id=r.related_customer_id",Customer.class)
                .setParameter(1, p_customer_id)
                .setParameter(2, p_bill_to)
                .setParameter(3, p_ship_to).getResultList();
    }

//    public Lookup findByName(String name) {
//        TypedQuery<Lookup> tq = em.createNamedQuery(Lookup.FIND_BY_NAME, Lookup.class).setParameter("p_name", name);
//        return tq.getSingleResult();
//    }
//
    // Мэппинг прописал в orm.xml, так как аннотации не воспринимает!!!
    //Long rwaddress_id, String rws_code, String rws_name, String rwBranch, String rwRcvCode
//    public List<RWAddressVL> findRWAddressVL(Long p_customer_id) {
//        List<RWAddressVL> a = em.createNativeQuery("select r.rwaddress_id, r.rws_code, r.rwrcvcode, r.rwbranch, r.activestatus, s.name as rws_name"
//                + " from RWAddress r, RWStationTL s "
//                + "where r.customer_id=?1 and s.rws_code=r.rws_code and "
//                + "s.language=?2 order by s.name", "RWAddressVLMapping").setParameter(1, p_customer_id).
//                setParameter(2, sa.getLanguage()).getResultList();
//        return a;
//    }
}
