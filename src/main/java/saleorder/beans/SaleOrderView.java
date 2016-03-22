/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saleorder.beans;

import com.isd.myjaxrs.entity.Item;
import com.isd.myjaxrs.entity.SaleOrder;
import com.isd.myjaxrs.entity.SaleOrderLine;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import json.item.ItemBackingBean;
import json.item.ItemClientBean;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
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
    private ItemClientBean itemClient;
    
    @Inject
    private ItemBackingBean itemBacking;

    @Inject
    private SaleOrderLineBackingBean sol;

    @Inject
    private SaleOrderLineBean solb;

    private List<SaleOrderLine> lines = null;

    private SaleOrder order;
    
    private Item item;

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
    }
    
    public void onItemSelect(SelectEvent event) {
        System.out.println("saleorder.beans.SaleOrderView.onItemSelect()"+itemBacking.getId());
//       itemBacking.setId(((Item)event.getObject()).getId());
    }

    public List<SaleOrderLine> getOrderLines() {
        return lines;
    }

    public SaleOrder getOrder() {
        return order;
    }

    public List<Item> completeItem(String query) {
        Item[] allItems = itemClient.getItems();
        List<Item> filteredItems = new ArrayList<>();

        for(Item item:allItems) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(item);
            }
        }

        return filteredItems;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    

}
