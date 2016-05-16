package so.saleorder.beans;

import so.entities.SaleOrder;
import so.entities.SaleOrderLine;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import so.config.beans.ConfigCBean;
import so.saleorder.flows.CreateSaleOrderFlow;
import so.saleorder.flows.CreateSaleOrderLineFlow;
import so.saleorderline.beans.SaleOrderLineCBean;

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
    private FindSaleOrderBackingBean sessionBean;
    @Inject
    private OpenSaleOrderView sov;

    @Inject
    private SaleOrderEJB orderEJB;

    @Inject
    private ConfigCBean configClient;

    @Inject
    private SaleOrderLineCBean slb;

    public SaleOrder getItem() {
        return orderEJB.find(sessionBean.getId());
    }

    public List<SaleOrder> getItems() {
        return orderEJB.findAll();
    }

    public Long getNewOrderNumber() {
        return orderEJB.orderNumber();
    }

    public List<SaleOrderLine> getLines() {
        return orderEJB.getOrderLines(sessionBean.getId());
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
        String status = orderEJB.remove(orderEJB.find(sessionBean.getId()));
        orderEJB.sendMessage(status, "Заказ удален успешно");
    }

    public void editItem() {
        SaleOrder order = sov.getOrder();
        String status = orderEJB.edit(order);
        orderEJB.sendMessage(status, "Заказ № " + order.getOrder_number() + " изменен");
        saveLines();
    }

    public void addLine() {
        SaleOrderLine line = new SaleOrderLine();
        line.setHeader_id(sessionBean.getId());
        line.setItem(sov.getItem());
        Short next_num = getMaxLineNum(sessionBean.getId());
        next_num++;
        line.setLine_num(next_num);
        line.setConfig_ver_num(configClient.getLastVersion(sov.getItem().getId()));
        slb.create(line, "Строка успешно добавлена");

        //updateListLines();
        try {
            sov.setOrder_lines(getLines(sessionBean.getId()));
        } catch (Exception ex) {

        }

        sov.setParameters(sov.getAllLinesAttributes());
    }

    public void deleteLines() {
        for (SaleOrderLine line : sov.getSelected_lines()) {
            slb.delete(line.getLine_id(), "Позиция " + line.getLine_num() + " удалена успешно");
        }

        sov.getOrder_lines().removeAll(sov.getSelectedItems());

        sov.getSelectedItems().clear();

        try {
            sov.setOrder_lines(getLines(sessionBean.getId()));
        } catch (Exception ex) {
        }
        
        sov.setParameters(sov.getAllLinesAttributes());
    }

    public void saveLines() {
        for (int a : sov.getLinesForSave()) {
            SaleOrderLine sl = sov.getOrder_lines().get(a);
            sl.setConfig_ver_num(configClient.getLastVersion(sl.getItem().getId()));
            slb.edit(sl, "Строка " + sl.getLine_num() + " сохранена успешно");
        }
        
        sov.getLinesForSave().clear();

    }

    public Short getMaxLineNum(Long p_header_id) {
        return orderEJB.getMaxLineNum(p_header_id);
    }

}
