package beans.so.config;

import entities.so.config.ParameterConfiguration;
import entities.so.config.ParameterConfigurationValues;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class NewParameterView implements Serializable {

    private ParameterConfiguration paramConfig = new ParameterConfiguration();
    private List<String> paramTypes;
    //private boolean onSelectedType;
    private boolean disabledCB = false;

    private List<ParameterConfigurationValues> values;
    private List<ParameterConfigurationValues> selectedValues;
    private int line_num;

    private String parameterValue;

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
        paramTypes = new ArrayList<>();
        for (Enum e : ParameterConfiguration.ParameterType.values()) {
            paramTypes.add(e.name());
        }
        values = new ArrayList<>();
        line_num = 0;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public void addParameterValue() {
        if (!parameterValue.trim().isEmpty()) {
            line_num++;
            ParameterConfigurationValues pcv = new ParameterConfigurationValues();
            pcv.setLine_num(line_num);
            pcv.setParameterValue(parameterValue);
            values.add(pcv);
            setParameterValue(null);
        }
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

//    public void setOnSelectedType(boolean onSelectedType) {
//        this.onSelectedType = onSelectedType;
//    }
    public boolean isDisabledCB() {
        return disabledCB;
    }

    public void setDisabledCB(boolean disabledCB) {
        this.disabledCB = disabledCB;
    }

//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
    public List<ParameterConfigurationValues> getSelectedValues() {
        return selectedValues;
    }

    public void setSelectedValues(List<ParameterConfigurationValues> selectedValues) {
        this.selectedValues = selectedValues;
    }

//    перенесен в ParameterCBean.deleteParameterValues  
//    public void deleteItems() {
//        for (ParameterConfigurationValues p : selectedValues) {
//            System.out.println(p.getLine_num());
//            values.remove(p);
//        }
//        selectedValues.clear();
//    }
    public int getLine_num() {
        return line_num;
    }

    public void setLine_num(int line_num) {
        this.line_num = line_num;
    }

}
