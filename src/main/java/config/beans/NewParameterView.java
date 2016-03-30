package config.beans;

import config.entity.ParameterConfiguration;
import java.io.Serializable;
import java.util.ArrayList;
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
public class NewParameterView implements Serializable {

    private ParameterConfiguration paramConfig = new ParameterConfiguration();
    private List<String> paramTypes;
    private boolean onSelectedType;
    private boolean disabledCB = false;

    @Inject
    private ParameterClientBean client;

    @PostConstruct
    private void init() {
        paramTypes = new ArrayList<>();
        for(Enum e:ParameterConfiguration.ParameterType.values()){
            paramTypes.add(e.name());
        }
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

    public boolean isDisabledCB() {
        return disabledCB;
    }

    public void setDisabledCB(boolean disabledCB) {
        this.disabledCB = disabledCB;
    }

    public ParameterClientBean getClient() {
        return client;
    }

    public void setClient(ParameterClientBean client) {
        this.client = client;
    }

}
