package config.beans;

import config.entity.ParameterConfiguration;
import config.entity.ParameterConfigurationValues;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenParameterView implements Serializable {

    private ParameterConfiguration param;

    @Inject
    private ParameterClientBean client;

    @PostConstruct
    private void init() {
        param = client.getItem();
    }

    public ParameterConfiguration getParam() {
        param.setValues(getValues());
        return param;
    }

    public void setParam(ParameterConfiguration param) {
        this.param = param;
    }

    public boolean isTableType() {
        try {
            return param.getParameterType().equals(ParameterConfiguration.ParameterType.TABLE);
        } catch (java.lang.NullPointerException e) {
            return false;
        }
    }

    public List<ParameterConfigurationValues> getValues() {
        return client.getValues();
    }

}
