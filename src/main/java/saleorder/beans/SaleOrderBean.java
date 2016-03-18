package saleorder.beans;

import saleorder.flows.CreateSaleOrderFlow;
import saleorder.flows.CreateSaleOrderLineFlow;
import com.isd.myjaxrs.entity.SaleOrder;
import com.isd.myjaxrs.entity.SaleOrderLine;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import utils.RestProviderWR;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SaleOrderBean extends RestProviderWR<SaleOrder> {

    @Inject
    private CreateSaleOrderFlow orderHeader;
    @Inject
    private CreateSaleOrderLineFlow orderLines;
    @Inject
    private SaleOrderBackingBean sob;

    private Client client;
    private WebTarget target;

    @PostConstruct
    private void init() {
        client = ClientBuilder.newClient();
//        client.register(ClientLoggingFilter.class);
        target = client.target("http://localhost:8080/MyJaxRs/webresources/saleorder/");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public SaleOrder getItem() {
        return target.path("{item}").resolveTemplate("item", sob.getId()).request().get(SaleOrder.class);
    }

    public SaleOrder[] getItems() {
        return target.request().get(SaleOrder[].class);
    }
    
    public Long getNewOrderNumber(){
        return target.path("/newOrderNumber").request().get(Long.class);
    }
    
    public List<SaleOrderLine> getLines() {
        //return em.createNamedQuery(SaleOrderLine.FIND_BY_HEADER_ID, SaleOrderLine.class).setParameter("p_header_id", sob.getId()).getResultList();
        return target.path("/{item}/lines").resolveTemplate("item", sob.getId()).request().get(new GenericType<List<SaleOrderLine>>(){});
    }
    
    public String addItem() throws Exception {
        SaleOrder order = new SaleOrder();
        order.setOrder_number(orderHeader.getOrder_number());
        order.setCustomer(orderHeader.getCustomer());
        order.setLines(orderLines.getLines());
        target
                .register(this)
                .request()
                .post(Entity.entity(order, MediaType.APPLICATION_JSON));
        return "goHome";
    }

    public void deleteItem() {
        target
                .path("{itemId}")
                .resolveTemplate("itemId", sob.getId())
                .request()
                .delete();
    }

    @Override
    protected Class<SaleOrder> getObj() {
        return SaleOrder.class;
    }

}
