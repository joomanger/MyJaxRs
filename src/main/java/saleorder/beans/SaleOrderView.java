/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saleorder.beans;

import com.isd.myjaxrs.entity.SaleOrder;
import com.isd.myjaxrs.entity.SaleOrderLine;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;
import saleorderline.beans.SaleOrderLineBackingBean;
import saleorderline.beans.SaleOrderLineBean;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class SaleOrderView implements Serializable {

    @Inject
    private SaleOrderBean sob;

    @Inject
    private SaleOrderLineBackingBean sol;

    @Inject
    private SaleOrderLineBean solb;

    private List<SaleOrderLine> lines = null;

    private SaleOrder order;

    @PostConstruct
    private void init() {
        lines = sob.getLines();
        order = sob.getItem();
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Строка " + String.valueOf(((SaleOrderLine) event.getObject()).getLine_num()) + " изменена");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        sol.setLine((SaleOrderLine) event.getObject());
        solb.edit();
    }

    public void onRowCancel(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Line Edited", String.valueOf(((SaleOrderLine) event.getObject()).getLine_num()));
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<SaleOrderLine> getOrderLines() {
        return lines;//sob.getLines();
    }

    public SaleOrder getOrder() {
        return order;
    }

//    public SaleOrder[] getOrders() {
//        return sob.getItems();
//    }
}
