package beans.customer;

import entities.customer.Customer;
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
        if (p_ship_to) {
            return em.createNativeQuery("select c.* from customer c where c.customer_id=?1 \n"
                    + "  and exists (select 1 from address s where s.customer_id=c.customer_id and s.ship_to=true)\n"
                    + "union all  \n"
                    + "select c.*\n"
                    + "from relationship r,\n"
                    + "     customer c \n"
                    + "where r.related_customer_id=?1 \n"
                    + "  and r.ship_to=true \n"
                    + "  and c.customer_id=r.customer_id\n"
                    + "  and c.activestatus=true", Customer.class)
                    .setParameter(1, p_customer_id)
                    .getResultList();
        } else {
            return em.createNativeQuery("select c.* from customer c where c.customer_id=?1 \n"
                    + "  and exists (select 1 from address s where s.customer_id=c.customer_id and s.bill_to=true)\n"
                    + "union all  \n"
                    + "select c.*\n"
                    + "from relationship r,\n"
                    + "     customer c \n"
                    + "where r.related_customer_id=?1 \n"
                    + "  and r.bill_to=true \n"
                    + "  and c.customer_id=r.customer_id\n"
                    + "  and c.activestatus=true", Customer.class)
                    .setParameter(1, p_customer_id)
                    .getResultList();
        }
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
