package config.beans;

import config.entity.Configuration;
import config.entity.ConfigurationLine;
import config.entity.ParameterConfiguration;
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
public class ConfigClientBean extends RestProviderWR<Configuration> {

    @Inject
    private NewConfigView newView;

    @Inject
    private FindConfigSession findSession;

    @Inject
    private OpenConfigView openView;

    @Override
    protected String getPath() {
        return ("http://localhost:8080/MyJaxRs/webresources/configuration/");
    }

    public Configuration getItem() {
        return super.getItem(Configuration.class, findSession.getConfig_id());
    }

    public Configuration getItem(Long p_item_id, Integer p_ver_num) {
        
        return getTarget().path("/{p_item_id}.{p_ver_num}").resolveTemplate("p_item_id", p_item_id).resolveTemplate("p_ver_num", p_ver_num).request().get(Configuration.class);
    }

    public Configuration[] getItems() {
        return super.getItems(ParameterConfiguration[].class);
    }

    public List<Configuration> getItemsList() {
        return Arrays.asList(super.getItems(Configuration[].class));
    }

    public List<ConfigurationLine> getLines() {
        return getTarget().path("/{header_id}/lines").resolveTemplate("header_id", findSession.getConfig_id()).request().get(new GenericType<List<ConfigurationLine>>() {
        });
    }

    public List<ConfigurationLine> getLines(Long p_config_id) {
        return getTarget().path("/{header_id}/lines").resolveTemplate("header_id", p_config_id).request().get(new GenericType<List<ConfigurationLine>>() {
        });
    }

    public Integer getMaxLineNum() {
        return getTarget().path("/{header_id}/max_line_num").resolveTemplate("header_id", findSession.getConfig_id()).request().get(Integer.class);
    }

    public Integer getMaxLineNum(Long p_config_id) {
        return getTarget().path("/{header_id}/max_line_num").resolveTemplate("header_id", p_config_id).request().get(Integer.class);
    }

    public String addItemTable() {
        Configuration config = newView.getConfiguration();
        config.setConfig_ver_num(getLastVersion() + 1);
        config.setLines(newView.getLines());
        Response t = super.editItem(config, "Конфигурация сохранена успешно");
        System.err.println(t.getStatusInfo());
        return "configs";
    }

    public Integer getLastVersion() {
        Integer version = getTarget().path("/version/{item_id}").resolveTemplate("item_id", newView.getConfiguration().getItem().getId()).request().get(Integer.class);
        if (version == null) {
            return 0;
        } else {
            return version;
        }
    }

    public Integer getLastVersion(Long p_item_id) {
        if (p_item_id != null) {
            Integer version = getTarget().path("/version/{item_id}").resolveTemplate("item_id", p_item_id).request().get(Integer.class);
            if (version == null) {
                return 0;
            } else {
                return version;
            }
        } else {
            return 0;
        }
    }

}
