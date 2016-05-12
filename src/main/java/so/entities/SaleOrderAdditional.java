/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
public class SaleOrderAdditional implements Serializable {

    @Id
    @SequenceGenerator(name = "order_header_add_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "order_header_add_sq")
    private long header_add_id;
    private int a = 1;
    private String b = "asad";

    @OneToOne(mappedBy = "soa")   
    private SaleOrder order;

    public long getHeader_add_id() {
        return header_add_id;
    }

    public void setHeader_add_id(long header_add_id) {
        this.header_add_id = header_add_id;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

}
