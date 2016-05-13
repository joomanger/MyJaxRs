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

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(item.beans.ItemClientBean.class);
        resources.add(item2.beans.ItemClientBean2.class);
        resources.add(service.Item2FacadeREST.class);
        resources.add(service.ItemFacadeREST.class);
        resources.add(so.config.beans.ConfigClientBean.class);
        resources.add(so.config.beans.ConfigLineClientBean.class);
        resources.add(ws.so.ConfigurationFacadeREST.class);
        resources.add(ws.so.ConfigurationLineFacadeREST.class);
        resources.add(ws.so.SaleOrderBean.class);
        resources.add(ws.so.SaleOrderFacadeREST.class);
        resources.add(ws.so.SaleOrderLineBean.class);
        resources.add(ws.so.SaleOrderLineFacadeREST.class);
    }

}
