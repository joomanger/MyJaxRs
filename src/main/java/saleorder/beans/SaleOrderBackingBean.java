/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saleorder.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@ApplicationScoped
public class SaleOrderBackingBean {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
