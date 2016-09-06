/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.saleorder.flows;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

    private long header_id;
    private long order_number;
    private String customer;
    
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
        order_number = sob.getNewOrderNumber();
    }

    public long getHeader_id() {
        return header_id;
    }

    public void setHeader_id(long header_id) {
        this.header_id = header_id;
    }

    public long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(long order_number) {
        this.order_number = order_number;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

}
