package saleorderline.beans;

import com.isd.myjaxrs.entity.SaleOrderLine;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class SaleOrderLineEJB extends AbstractEJB<SaleOrderLine> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public SaleOrderLineEJB() {
        super(SaleOrderLine.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*---Дополнительные методы---*/
}
