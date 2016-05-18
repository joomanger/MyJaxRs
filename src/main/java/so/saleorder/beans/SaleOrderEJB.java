package so.saleorder.beans;

import so.entities.SaleOrder;
import so.entities.SaleOrderLine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class SaleOrderEJB extends AbstractEJB<SaleOrder> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public SaleOrderEJB() {
        super(SaleOrder.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*---Дополнительные методы---*/
    public List<SaleOrderLine> getOrderLines(Long id) {
        TypedQuery<SaleOrderLine> tq = em.createNamedQuery(SaleOrderLine.FIND_BY_HEADER_ID, SaleOrderLine.class).setParameter("p_header_id", id);
        return tq.getResultList();
    }

    public Long orderNumber() {
        return (Long) em.createNativeQuery("select nextval('order_header_number_sq')").getSingleResult();
    }

    public Short getMaxLineNum(Long id) {
        TypedQuery<Short> tq = em.createNamedQuery(SaleOrderLine.MAX_LINE_NUM_BY_HEADER_ID, Short.class).setParameter("p_header_id", id);
        return tq.getSingleResult();
    }

}
