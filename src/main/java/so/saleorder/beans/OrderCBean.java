package so.saleorder.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;
import so.entities.Order;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class OrderCBean extends AbstractClientBean<Order> {

//    @Inject
//    private CreateSaleOrderFlow orderFlow;

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
        ejb.clearFieldsHeader();
    }

}
