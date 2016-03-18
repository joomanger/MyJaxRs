package json.item2;

import com.isd.myjaxrs.entity.Item2;
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
public class ItemClientBean2 extends RestProviderWR<Item2>{
    @Inject
    private ItemBackingBean2 bean;
    private Client client;
    private WebTarget target;

    @PostConstruct
    private void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/MyJaxRs/webresources/item2/");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Item2 getItem() {
        return target.path("{item}").resolveTemplate("item", bean.getId()).request().get(Item2.class);
    }

    public Item2[] getItems() {
        return target.request().get(Item2[].class);
    }

    public void addItem() throws Exception {
        Item2 m = new Item2();
        //m.setId(bean.getId());
        m.setName(bean.getName());
        m.setAge(bean.getAge());
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
    protected Class<Item2> getObj() {
        return Item2.class;
    }

}
