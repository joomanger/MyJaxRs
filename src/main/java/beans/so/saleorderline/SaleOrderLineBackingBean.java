/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.so.saleorderline;

import entities.so.SaleOrderLine;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class SaleOrderLineBackingBean implements Serializable{
    private SaleOrderLine line;

    public SaleOrderLine getLine() {
        return line;
    }

    public void setLine(SaleOrderLine line) {
        this.line = line;
    }
    
}
