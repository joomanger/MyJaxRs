package saleorder.beans;

import com.isd.myjaxrs.entity.SaleOrder;
import com.isd.myjaxrs.entity.SaleOrderLine;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import saleorder.flows.CreateSaleOrderFlow;
import saleorder.flows.CreateSaleOrderLineFlow;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class SaleOrderCBean {

    @Inject
    private CreateSaleOrderFlow orderHeader;
    @Inject
    private CreateSaleOrderLineFlow orderLines;
    @Inject
    private FindSaleOrderBackingBean sob;
    @Inject
    private SaleOrderView sov;

    @Inject
    private SaleOrderEJB orderEJB;

    public SaleOrder getItem() {
        return orderEJB.find(sob.getId());
    }

    public SaleOrder[] getItems() {
        return (SaleOrder[]) (orderEJB.findAll().toArray());
    }

    public Long getNewOrderNumber() {
        return orderEJB.orderNumber();
    }

    public List<SaleOrderLine> getLines() {
        return orderEJB.getOrderLines(sob.getId());
    }

    public List<SaleOrderLine> getLines(Long p_header_id) {
        return orderEJB.getOrderLines(p_header_id);
    }

    public String addItem() {

        SaleOrder order = new SaleOrder();
        order.setOrder_number(orderHeader.getOrder_number());
        order.setCustomer(orderHeader.getCustomer());
        order.setLines(orderLines.getLines());
        String status = orderEJB.create(order);
        orderEJB.sendMessage(status, "Заказ №" + orderHeader.getOrder_number() + " добавлен успешно");
        return "goHome";
    }

    public void deleteItem() {
        String status = orderEJB.remove(orderEJB.find(sob.getId()));
        orderEJB.sendMessage(status, "Заказ удален успешно");
    }

    public void editItem() {
        SaleOrder order = sov.getOrder();
        String status = orderEJB.edit(order);
        orderEJB.sendMessage(status, "Заказ № " + order.getOrder_number() + " изменен");
    }

    public Short getMaxLineNum(Long p_header_id) {
        return orderEJB.getMaxLineNum(p_header_id);
    }

}
