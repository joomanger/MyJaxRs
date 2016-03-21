/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saleorderline.beans;

import com.isd.myjaxrs.entity.SaleOrderLine;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@ApplicationScoped
public class SaleOrderLineBackingBean {
    private SaleOrderLine line;

    public SaleOrderLine getLine() {
        return line;
    }

    public void setLine(SaleOrderLine line) {
        this.line = line;
    }
    
}
