/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isd.myjaxrs.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
public class SaleOrder implements Serializable {

    @Id
    @SequenceGenerator(name = "order_header_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "order_header_sq")
    private long header_id;
    private int order_number;
    private String customer;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "header_id")
    private List<SaleOrderLine> lines;

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

    public List<SaleOrderLine> getLines() {
        return lines;
    }

    public void setLines(List<SaleOrderLine> lines) {
        this.lines = lines;
    }

}
