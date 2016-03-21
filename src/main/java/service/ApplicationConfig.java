/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author savin
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(json.item.ItemClientBean.class);
        resources.add(json.item2.ItemClientBean2.class);
        resources.add(saleorder.RESTS.SaleOrderFacadeREST.class);
        resources.add(saleorder.RESTS.SaleOrderLineFacadeREST.class);
        resources.add(saleorder.beans.SaleOrderBean.class);
        resources.add(saleorderline.beans.SaleOrderLineBean.class);
        resources.add(service.Item2FacadeREST.class);
        resources.add(service.ItemFacadeREST.class);
    }
    
}
