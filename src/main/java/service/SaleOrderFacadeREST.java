/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.isd.myjaxrs.entity.SaleOrder;
import com.isd.myjaxrs.entity.SaleOrderLine;
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

/**
 *
 * @author savin
 */
@Stateless
@Path("saleorder")
public class SaleOrderFacadeREST extends AbstractFacade<SaleOrder> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public SaleOrderFacadeREST() {
        super(SaleOrder.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(SaleOrder entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, SaleOrder entity) {
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
    public SaleOrder find(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @GET
    @Path("/{header_id}/lines")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SaleOrderLine> getOrderLines(@PathParam("header_id") Long id) {
        TypedQuery<SaleOrderLine> tq= em.createNamedQuery(SaleOrderLine.FIND_BY_HEADER_ID, SaleOrderLine.class).setParameter("p_header_id", id);
        return tq.getResultList();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @OrderBy("order_number desc")
    public List<SaleOrder> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SaleOrder> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("newOrderNumber")
    @Produces(MediaType.TEXT_PLAIN)
    public Long orderNumber() {
        return (Long) em.createNativeQuery("select nextval('order_header_number_sq')").getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
