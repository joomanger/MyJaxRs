package config.beans;

import config.entity.ParameterConfiguration;
import config.entity.ParameterConfigurationValues;
import java.util.Arrays;
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
import service.RestProviderWR;

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

    @Inject
    private FindParameterSession fpv;

    @Inject
    private OpenParameterView opv;

    @Override
    protected String getPath() {
        return ("http://localhost:8080/MyJaxRs/webresources/parameterconfiguration/");
    }

    public ParameterConfiguration getItem() {
        return super.getItem(ParameterConfiguration.class, fpv.getParamID());
    }

    public ParameterConfiguration[] getItems() {
        return super.getItems(ParameterConfiguration[].class);
    }

    public List<ParameterConfiguration> getItemsList() {
        return Arrays.asList(super.getItems(ParameterConfiguration[].class));
    }

    public List<ParameterConfigurationValues> getValues() {
        return getTarget().path("/{header_id}/lines").resolveTemplate("header_id", fpv.getParamID()).request().get(new GenericType<List<ParameterConfigurationValues>>() {
        });
    }
    
    public List<ParameterConfigurationValues> getValues(Long p_header_id) {
        return getTarget().path("/{header_id}/lines").resolveTemplate("header_id", p_header_id).request().get(new GenericType<List<ParameterConfigurationValues>>() {
        });
    }

    public Integer getMaxLineNum() {
        return getTarget().path("/{header_id}/max_line_num").resolveTemplate("header_id", fpv.getParamID()).request().get(Integer.class);
    }

    public String editItem() {
        ParameterConfiguration pc = opv.getParam();
        super.editItem(pc, "Параметр " + pc.getName() + " обновлен успешно");
        return "params";
    }

    public String addItem() {
        ParameterConfiguration p = npv.getParamConfig();
        if (p.getParameterType() != ParameterConfiguration.ParameterType.TABLE) {
            Response t = super.addItem(p, "Параметр " + p.getName() + " добавлен успешно");
            if (t.getStatus() != 200) {
                System.out.println("djopa!");
                return null;
            } else {
                return "params";
            }
        } else {
            npv.setDisabledCB(true);
            return null;
        }
    }

    public String addItemTable() {
        ParameterConfiguration p = npv.getParamConfig();
        p.setValues(npv.getValues());
        Response t = super.addItem(p, "Параметр " + p.getName() + " добавлен успешно");
        if (t.getStatus() != 200) {
            System.out.println("djopa!");
        }
        return "params";
    }

}
