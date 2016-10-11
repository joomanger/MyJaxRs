package so.saleorder.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB;
import so.entities.Order;
import so.saleorder.flows.CreateSaleOrderFlow;

/**
 *
 * @author savin
 */
@Stateless
public class OrderEJB extends AbstractEJB<Order> {
    
    @Inject
    private CreateSaleOrderFlow orderFlow;

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public OrderEJB() {
        super(Order.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /*После выбора нового Заказчика обнуляются все зависимые поля*/
    public void clearFieldsHeader() {
        orderFlow.getOrder().setShp_customer(null);
        orderFlow.getOrder().setShp_address(null);
        orderFlow.getOrder().setShp_rwaddress(null);
        orderFlow.getOrder().setInv_customer(null);
        orderFlow.getOrder().setInv_address(null);
    }

}
