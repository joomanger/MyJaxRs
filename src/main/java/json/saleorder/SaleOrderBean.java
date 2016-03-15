package json.saleorder;

import com.isd.myjaxrs.entity.SaleOrder;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import json.generic.RestProviderWR;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    private Client client;
    private WebTarget target;

    @PostConstruct
    private void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/MyJaxRs/webresources/saleorder/");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public SaleOrder getItem() {
        return target.path("{item}").resolveTemplate("item", orderHeader.getHeader_id()).request().get(SaleOrder.class);
    }

    public SaleOrder[] getItems() {
        return target.request().get(SaleOrder[].class);
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
                .resolveTemplate("itemId", orderHeader.getHeader_id())
                .request()
                .delete();
    }

    @Override
    protected Class<SaleOrder> getObj() {
        return SaleOrder.class;
    }

}
