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
        resources.add(config.beans.ParameterClientBean.class);
        resources.add(config.beans.ParameterValuesClientBean.class);
        resources.add(config.rests.ParameterConfigurationFacadeREST.class);
        resources.add(config.rests.ParameterConfigurationValuesFacadeREST.class);
        resources.add(item.beans.ItemClientBean.class);
        resources.add(item2.beans.ItemClientBean2.class);
        resources.add(saleorder.beans.SaleOrderBean.class);
        resources.add(saleorder.rests.SaleOrderFacadeREST.class);
        resources.add(saleorder.rests.SaleOrderLineFacadeREST.class);
        resources.add(saleorderline.beans.SaleOrderLineBean.class);
        resources.add(service.Item2FacadeREST.class);
        resources.add(service.ItemFacadeREST.class);
    }
    
}
