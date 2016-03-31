package config.beans;

import config.entity.ParameterConfiguration;
import config.entity.ParameterConfigurationValues;
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

    private List<ParameterConfigurationValues> values;
    private int line_num;

    private String value;

    @PostConstruct
    private void init() {
        paramTypes = new ArrayList<>();
        for (Enum e : ParameterConfiguration.ParameterType.values()) {
            paramTypes.add(e.name());
        }
        values = new ArrayList<>();
        line_num = 0;
    }

    public void addValue() {
        line_num++;
        ParameterConfigurationValues pcv = new ParameterConfigurationValues();
        pcv.setLine_num(line_num);
        pcv.setParameterValue(getValue());
        values.add(pcv);
    }

    public List<ParameterConfigurationValues> getValues() {
        return values;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
