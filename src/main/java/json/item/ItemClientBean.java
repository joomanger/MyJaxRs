package json.item;

import com.isd.myjaxrs.entity.Item;
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
public class ItemClientBean extends RestProviderWR<Item>{
    @Inject
    private ItemBackingBean bean;
    private Client client;
    private WebTarget target;

    @PostConstruct
    private void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/MyJaxRs/webresources/item/");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Item getItem() {
        return target.path("{item}").resolveTemplate("item", bean.getId()).request().get(Item.class);
    }

    public Item[] getItems() {
        return target.request().get(Item[].class);
    }

    public void addItem() throws Exception {
        Item m = new Item();
        m.setId(bean.getId());
        m.setName(bean.getName());
        m.setDescription(bean.getDescription());
        target
                .register(this)
                .request()
                .post(Entity.entity(m, MediaType.APPLICATION_JSON));
    }

    public void deleteItem() {
        target
                .path("{itemId}")
                .resolveTemplate("itemId", bean.getId())
                .request()
                .delete();
    }

    @Override
    protected Class<Item> getObj() {
        return Item.class;
    }

}
