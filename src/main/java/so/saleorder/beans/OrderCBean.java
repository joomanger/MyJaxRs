package so.saleorder.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;
import so.entities.Order;
import so.saleorder.flows.CreateSaleOrderFlow;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class OrderCBean extends AbstractClientBean<Order> {

    @Inject
    private CreateSaleOrderFlow orderFlow;

    @Inject
    private OrderEJB ejb;

    @Override
    protected AbstractEJB<Order> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<Order> getOpenView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected AbstractView<Order> getFindView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected AbstractView<Order> getNewView() {
        throw new UnsupportedOperationException("Not supported yet.");
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
