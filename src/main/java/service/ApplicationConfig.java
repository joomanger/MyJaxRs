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
        //addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(item.beans.ItemClientBean.class);
        resources.add(org.eclipse.persistence.jpa.rs.exceptions.JPARSExceptionMapper.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.EntityResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.MetadataResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.PersistenceResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.PersistenceUnitResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.QueryResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.SingleResultQueryResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.unversioned.EntityResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.unversioned.PersistenceResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.unversioned.PersistenceUnitResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.unversioned.QueryResource.class);
        resources.add(org.eclipse.persistence.jpa.rs.resources.unversioned.SingleResultQueryResource.class);
        resources.add(service.ItemFacadeREST.class);
        resources.add(ws.so.ConfigurationFacadeREST.class);
        resources.add(ws.so.ConfigurationLineFacadeREST.class);
        resources.add(ws.so.SaleOrderBean.class);
        resources.add(ws.so.SaleOrderFacadeREST.class);
        resources.add(ws.so.SaleOrderLineBean.class);
        resources.add(ws.so.SaleOrderLineFacadeREST.class);
    }

}
