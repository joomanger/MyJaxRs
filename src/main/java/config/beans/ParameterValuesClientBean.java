package config.beans;

import config.entity.ParameterConfigurationValues;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
public class ParameterValuesClientBean extends RestProviderWR<ParameterConfigurationValues> {

    private ParameterConfigurationValues value;

    public ParameterConfigurationValues getValue() {
        return value;
    }

    public void setValue(ParameterConfigurationValues value) {
        this.value = value;
    }

    @Override
    protected String getPath() {
        return ("http://localhost:8080/MyJaxRs/webresources/parameterconfigurationvalues/");
    }

    public void editItem() {
        super.editItem(value, "Параметр обновлен успешно");

    }

}
