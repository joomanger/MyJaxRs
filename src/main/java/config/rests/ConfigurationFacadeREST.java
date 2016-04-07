package config.rests;

import config.entity.Configuration;
import config.entity.ConfigurationLine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
@Path("configuration")
public class ConfigurationFacadeREST extends AbstractFacade<Configuration> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public ConfigurationFacadeREST() {
        super(Configuration.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Configuration entity) {
        return super.create(entity);
    }

    @PUT
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Configuration entity) {
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
    public Configuration find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Configuration> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Configuration> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("/{header_id}/lines")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ConfigurationLine> getValues(@PathParam("header_id") Long id) {
        TypedQuery<ConfigurationLine> tq = em.createNamedQuery(ConfigurationLine.FIND_BY_HEADER_ID, ConfigurationLine.class).setParameter("p_header_id", id);
        return tq.getResultList();
    }

    @GET
    @Path("/{p_item_id}.{p_ver_num}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Configuration getConfig (@PathParam("p_item_id") Long p_item_id, @PathParam("p_ver_num") Integer p_ver_num) {
        TypedQuery<Configuration> tq = em.createNamedQuery(Configuration.FIND_HEADER_ID_BY_LAST_VER_NUM_AND_ITEM_ID, Configuration.class)
                .setParameter("p_item_id", p_item_id)
                .setParameter("p_ver_num", p_ver_num);
        try{
        Configuration c= tq.getSingleResult();
        return c;
        }catch(javax.persistence.NoResultException ex){
            return null;
        }
    }

    @GET
    @Path("/version/{item_id}")
    @Produces({MediaType.TEXT_PLAIN})
    public Integer getLastVersion(@PathParam("item_id") Long id) {
        TypedQuery<Integer> tq = em.createNamedQuery(Configuration.FIND_LAST_VERSION_BY_ITEM, Integer.class).setParameter("p_item_id", id);
        return tq.getSingleResult();
    }

    @GET
    @Path("/{header_id}/max_line_num")
    @Produces({MediaType.TEXT_PLAIN})
    public Integer getMaxLineNum(@PathParam("header_id") Long id) {
        TypedQuery<Integer> tq = em.createNamedQuery(ConfigurationLine.MAX_LINE_NUM_BY_HEADER_ID, Integer.class).setParameter("p_header_id", id);
        return tq.getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
