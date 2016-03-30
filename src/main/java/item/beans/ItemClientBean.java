package item.beans;

import com.isd.myjaxrs.entity.Item;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import utils.RestProviderWR;

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
public class ItemClientBean extends RestProviderWR<Item> {

    @Inject
    private ItemBackingBean bean;

    @Override
    protected String getPath() {
        return ("http://localhost:8080/MyJaxRs/webresources/item/");
    }

    public Item getItem() {
        //return getTarget().path("{item}").resolveTemplate("item", bean.getId()).request().get(Item.class);
        return super.getItem(Item.class, bean.getId());
    }

    public Item[] getItems() {
        return super.getItems(Item[].class);
    }

    public void addItem() {
        Item m = new Item();
        m.setId(bean.getId());
        m.setName(bean.getName());
        m.setDescription(bean.getDescription());
        getTarget()
                .register(this)
                .request()
                .post(Entity.entity(m, MediaType.APPLICATION_JSON));
    }

    public void deleteItem() {
        super.deleteItem(bean.getId());
    }

}
