/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.so;

import entities.item.Item;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    private long order_number;
    private String customer;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "soa_fk")
    private SaleOrderAdditional soa = new SaleOrderAdditional();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "header_id")
    private List<SaleOrderLine> lines;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    public SaleOrderAdditional getSoa() {
        return soa;
    }

    public void setSoa(SaleOrderAdditional soa) {
        this.soa = soa;
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

    @XmlTransient
    public List<SaleOrderLine> getLines() {
        return lines;
    }

    public void setLines(List<SaleOrderLine> lines) {
        this.lines = lines;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
