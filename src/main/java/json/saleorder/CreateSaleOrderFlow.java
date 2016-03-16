/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.saleorder;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author savin
 */
@Named
@FlowScoped("order")
public class CreateSaleOrderFlow implements Serializable {

    private long header_id;
    private long order_number;
    private String customer;

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    @PostConstruct
    private void init() {
        order_number = (Long) em.createNativeQuery("select nextval('order_header_number_sq')").getSingleResult();
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
