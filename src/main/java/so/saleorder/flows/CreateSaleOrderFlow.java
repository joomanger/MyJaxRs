package so.saleorder.flows;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import so.entities.Order;
import so.saleorder.beans.SaleOrderCBean;

/**
 *
 * @author savin
 */
@Named
@FlowScoped("newOrder")
public class CreateSaleOrderFlow implements Serializable {

    @Inject
    private SaleOrderCBean sob;

    private Order order;

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
        order = new Order();
        order.setHeader_id(sob.getNewOrderNumber());
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
