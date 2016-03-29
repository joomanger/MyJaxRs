package item2.beans;

import com.isd.myjaxrs.entity.Item2;
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
public class ItemClientBean2 extends RestProviderWR<Item2> {

    @Inject
    private ItemBackingBean2 bean;

    @Override
    protected String getPath() {
        return ("http://localhost:8080/MyJaxRs/webresources/item2/");
    }

    public Item2 getItem() {
        return super.getItem(Item2.class,bean.getId());
    }

    public Item2[] getItems() {
        return super.getItems(Item2[].class);
    }

    public void addItem() throws Exception {
        Item2 m = new Item2();
        //m.setId(bean.getId());
        m.setName(bean.getName());
        m.setAge(bean.getAge());
        getTarget()
                .register(this)
                .request()
                .post(Entity.entity(m, MediaType.APPLICATION_JSON));
    }

    public void deleteItem() {
        super.deleteItem(bean.getId());
    }
}
