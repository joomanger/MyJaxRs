/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saleorderline.beans;

import com.isd.myjaxrs.entity.SaleOrderLine;
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
public class SaleOrderLineBean extends RestProviderWR<SaleOrderLine> {

    private Client client;
    private WebTarget target;

    @Inject
    private SaleOrderLineBackingBean sob;

    @PostConstruct
    private void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/MyJaxRs/webresources/saleorderline/");
    }

    public void edit() {
        //target.path("{itemId}").resolveTemplate("itemId", sob.getLine().getLine_id()).request().put(Entity.entity(sob.getLine(), MediaType.APPLICATION_JSON));
        //System.out.println("EDIT:"+sob.getLine().getLine_num()+" qty="+sob.getLine().getQuantity());
         target
                .register(this)
                .request()
                .put(Entity.entity(sob.getLine(), MediaType.APPLICATION_JSON));
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    @Override
    protected Class<SaleOrderLine> getObj() {
        return SaleOrderLine.class;
    }

}
