/*
 */
package config.rests;

import config.entity.ParameterConfiguration;
import config.entity.ParameterConfigurationValues;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.AbstractFacade;

/**
 *
 * @author savin
 */
@Stateless
@Path("parameterconfiguration")
public class ParameterConfigurationFacadeREST extends AbstractFacade<ParameterConfiguration> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public ParameterConfigurationFacadeREST() {
        super(ParameterConfiguration.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(ParameterConfiguration entity) {
        return super.create(entity);
        
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public void edit(ParameterConfiguration entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ParameterConfiguration find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @OrderBy(value = "name")
    public List<ParameterConfiguration> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ParameterConfiguration> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("/{header_id}/lines")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ParameterConfigurationValues> getValues(@PathParam("header_id") Long id) {
        TypedQuery<ParameterConfigurationValues> tq = em.createNamedQuery(ParameterConfigurationValues.FIND_BY_HEADER_ID, ParameterConfigurationValues.class).setParameter("p_header_id", id);
        return tq.getResultList();
    }
    @GET
    @Path("/{header_id}/max_line_num")
    @Produces({MediaType.TEXT_PLAIN})
    public Integer getMaxLineNum(@PathParam("header_id") Long id) {
        TypedQuery<Integer> tq= em.createNamedQuery(ParameterConfigurationValues.MAX_LINE_NUM_BY_HEADER_ID, Integer.class).setParameter("p_header_id", id);
        return tq.getSingleResult();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
