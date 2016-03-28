/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isd.myjaxrs.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
@NamedQuery(name = SaleOrderLine.FIND_BY_HEADER_ID, query = "select b from SaleOrderLine b where b.header_id=:p_header_id order by b.line_num")
public class SaleOrderLine implements Serializable {

    public static final String FIND_BY_HEADER_ID = "FBHI";
    @Id
    @SequenceGenerator(name = "order_line_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "order_line_sq")
    private long line_id;
    private String item;
    private short line_num;
    private Double quantity;
    private Double price;
    private Long header_id;

    public long getLine_id() {
        return line_id;
    }

    public void setLine_id(long line_id) {
        this.line_id = line_id;
    }

    public short getLine_num() {
        return line_num;
    }

    public void setLine_num(short line_num) {
        this.line_num = line_num;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getHeader_id() {
        return header_id;
    }

    public void setHeader_id(Long header_id) {
        this.header_id = header_id;
    }

}
