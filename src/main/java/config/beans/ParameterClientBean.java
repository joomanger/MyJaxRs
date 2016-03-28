package config.beans;

import config.entity.ParameterConfiguration;
import config.entity.ParameterConfigurationValues;
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
public class ParameterClientBean extends RestProviderWR<ParameterConfiguration> {
    @Inject
    private NewParameterView paramView;
    
    private Client client;
    private WebTarget target;

    @PostConstruct
    private void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/MyJaxRs/webresources/parameterconfiguration/");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public ParameterConfiguration getItem() {
        return target.path("{item}").resolveTemplate("item", 1).request().get(ParameterConfiguration.class);
    }

    public ParameterConfiguration[] getItems() {
        return target.request().get(ParameterConfiguration[].class);
    }

    public List<ParameterConfigurationValues> getValues() {
        return target.path("/{item}/values").resolveTemplate("item", 1).request().get(new GenericType<List<ParameterConfigurationValues>>() {
        });
    }

    public void deleteItem() {
        target
                .path("{itemId}")
                .resolveTemplate("itemId", 1)
                .request()
                .delete();
    }

    @Override
    protected Class<ParameterConfiguration> getObj() {
        return ParameterConfiguration.class;
    }

}
