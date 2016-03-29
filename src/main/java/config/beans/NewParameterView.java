package config.beans;

import config.entity.ParameterConfiguration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class NewParameterView implements Serializable {

    private ParameterConfiguration paramConfig = new ParameterConfiguration();
    private List<String> paramTypes;
    private boolean onSelectedType;
    
    @Inject
    private ParameterClientBean client;

    @PostConstruct
    private void init() {
        paramTypes = new ArrayList<>();
        paramTypes.add("DIGIT");
        paramTypes.add("STRING");
        paramTypes.add("TABLE");
        
    }

    public ParameterConfiguration getParamConfig() {
        return paramConfig;
    }

    public void setParamConfig(ParameterConfiguration paramConfig) {
        this.paramConfig = paramConfig;
    }

    public List<String> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<String> paramTypes) {
        this.paramTypes = paramTypes;
    }

    public void selT() {
        try {

            System.out.println(onSelectedType);
        } catch (java.lang.NullPointerException e) {
            System.out.println(e.getMessage());
            onSelectedType = false;
        }
    }

    public boolean isOnSelectedType() {
        try {
            return paramConfig.getParameterType().equals(ParameterConfiguration.ParameterType.TABLE);
        } catch (java.lang.NullPointerException e) {
            return false;
        }

    }

    public void setOnSelectedType(boolean onSelectedType) {
        this.onSelectedType = onSelectedType;
    }
   
}
