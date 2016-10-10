package so.saleorder.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB;
import so.entities.Order;

/**
 *
 * @author savin
 */
@Stateless
public class OrderEJB extends AbstractEJB<Order> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public OrderEJB() {
        super(Order.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
