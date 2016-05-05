package saleorder.beans;

import saleorder.flows.CreateSaleOrderFlow;
import saleorder.flows.CreateSaleOrderLineFlow;
import com.isd.myjaxrs.entity.SaleOrder;
import com.isd.myjaxrs.entity.SaleOrderLine;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import service.RestProviderWR;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SaleOrderBean extends RestProviderWR<SaleOrder> {

    @Inject
    private CreateSaleOrderFlow orderHeader;
    @Inject
    private CreateSaleOrderLineFlow orderLines;
    @Inject
    private FindSaleOrderBackingBean sob;
    @Inject
    private SaleOrderView sov;

    @Override
    protected String getPath() {
        return ("http://localhost:8080/MyJaxRs/webresources/saleorder/");
    }

    public SaleOrder getItem() {
        return super.getItem(SaleOrder.class, sob.getId());
    }

    public SaleOrder[] getItems() {
        return super.getItems(SaleOrder[].class);
    }

    public Long getNewOrderNumber() {
        return getTarget().path("/newOrderNumber").request().get(Long.class);
    }

    public List<SaleOrderLine> getLines() {
        return getTarget().path("/{header_id}/lines").resolveTemplate("header_id", sob.getId()).request().get(new GenericType<List<SaleOrderLine>>() {
        });
    }
    
    public List<SaleOrderLine> getLines(Long p_header_id) {
        return getTarget().path("/{header_id}/lines").resolveTemplate("header_id", p_header_id).request().get(new GenericType<List<SaleOrderLine>>() {
        });
    }

    public String addItem() {

        SaleOrder order = new SaleOrder();
        order.setOrder_number(orderHeader.getOrder_number());
        order.setCustomer(orderHeader.getCustomer());
        order.setLines(orderLines.getLines());
        Response t = super.addItem(order, "Заказ №" + orderHeader.getOrder_number() + " добавлен успешно");
        return "goHome";
    }

    public void deleteItem() {
        super.deleteItem(sob.getId(),"Заказ удален успешно");
    }

    public void editItem() {
        SaleOrder order = sov.getOrder();
        super.editItem(order,"Заказ № " + order.getOrder_number() + " изменен");
    }
    
    public Short getMaxLineNum(Long p_header_id) {
        return getTarget().path("/{header_id}/max_line_num").resolveTemplate("header_id", p_header_id).request().get(Short.class);
    }

}
