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
        //return super.getItem(SaleOrder.class, sob.getId());
        return orderEJB.find(sob.getId());
    }

    public SaleOrder[] getItems() {
        //return super.getItems(SaleOrder[].class);
        return (SaleOrder[]) (orderEJB.findAll().toArray());
    }

    public Long getNewOrderNumber() {
        //return getTarget().path("/newOrderNumber").request().get(Long.class);
        return orderEJB.orderNumber();
    }

    public List<SaleOrderLine> getLines() {
//        return getTarget().path("/{header_id}/lines").resolveTemplate("header_id", sob.getId()).request().get(new GenericType<List<SaleOrderLine>>() {
//        });
        return orderEJB.getOrderLines(sob.getId());
    }

    public List<SaleOrderLine> getLines(Long p_header_id) {
//        return getTarget().path("/{header_id}/lines").resolveTemplate("header_id", p_header_id).request().get(new GenericType<List<SaleOrderLine>>() {
//        });
        return orderEJB.getOrderLines(p_header_id);
    }

    public String addItem() {

        SaleOrder order = new SaleOrder();
        order.setOrder_number(orderHeader.getOrder_number());
        order.setCustomer(orderHeader.getCustomer());
        order.setLines(orderLines.getLines());
        //Response t = super.addItem(order, "Заказ №" + orderHeader.getOrder_number() + " добавлен успешно");
        String status = orderEJB.create(order);
        orderEJB.sendMessage(status, "Заказ №" + orderHeader.getOrder_number() + " добавлен успешно");
        return "goHome";
    }

    public void deleteItem() {
        //super.deleteItem(sob.getId(), "Заказ удален успешно");
        String status = orderEJB.remove(orderEJB.find(sob.getId()));
        orderEJB.sendMessage(status, "Заказ удален успешно");
    }

    public void editItem() {
        SaleOrder order = sov.getOrder();
//        super.editItem(order, "Заказ № " + order.getOrder_number() + " изменен");
        String status = orderEJB.edit(order);
        orderEJB.sendMessage(status, "Заказ № " + order.getOrder_number() + " изменен");
    }

    public Short getMaxLineNum(Long p_header_id) {
        //return getTarget().path("/{header_id}/max_line_num").resolveTemplate("header_id", p_header_id).request().get(Short.class);
        return orderEJB.getMaxLineNum(p_header_id);
    }

}
