package config.beans;

import config.entity.ParameterConfiguration;
import javax.faces.bean.ViewScoped;

import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewParameterView {
    private ParameterConfiguration paramConfig=new ParameterConfiguration();

    public ParameterConfiguration getParamConfig() {
        return paramConfig;
    }

    public void setParamConfig(ParameterConfiguration paramConfig) {
        this.paramConfig = paramConfig;
    }
    
    
}
