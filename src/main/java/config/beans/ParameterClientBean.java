package config.beans;

import config.entity.ParameterConfiguration;
import config.entity.ParameterConfigurationValues;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    private NewParameterView npv;

    @Override
    protected String getPath() {
        return ("http://localhost:8080/MyJaxRs/webresources/parameterconfiguration/");
    }

    public ParameterConfiguration getItem() {
        return getTarget().path("{item}").resolveTemplate("item", 1).request().get(ParameterConfiguration.class);
    }

    public ParameterConfiguration[] getItems() {
        return super.getItems(ParameterConfiguration[].class);
    }

    public List<ParameterConfigurationValues> getValues() {
        return getTarget().path("/{item}/values").resolveTemplate("item", 1).request().get(new GenericType<List<ParameterConfigurationValues>>() {
        });
    }

    public void updateItem() {
        super.editItem(npv.getParamConfig());
    }

    public void deleteItem() {

    }

    public void addItem() {
        ParameterConfiguration p = npv.getParamConfig();
        if (p.getParameterType() != ParameterConfiguration.ParameterType.TABLE) {
            Response t = super.addItem(p, "Параметр " + p.getName() + " добавлен успешно");
            if (t.getStatus() != 200) {
                System.out.println("djopa!");
            }
        } else {
            npv.setDisabledCB(true);
        }

    }

}
