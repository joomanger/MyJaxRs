/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.saleorder;

import java.io.Serializable;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@FlowScoped("order")
public class CreateSaleOrderFlow implements Serializable {
    private long header_id;
    private int order_number;
    private String customer;

    public long getHeader_id() {
        return header_id;
    }

    public void setHeader_id(long header_id) {
        this.header_id = header_id;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
    
}
