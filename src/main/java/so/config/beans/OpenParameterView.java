package so.config.beans;

import so.config.entity.ParameterConfiguration;
import so.config.entity.ParameterConfigurationValues;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenParameterView implements Serializable {

    private ParameterConfiguration param;
    private String newValue;
    private List<ParameterConfigurationValues> values = new ArrayList<>();
    private List<ParameterConfigurationValues> selectedValues = new ArrayList<>();

    @Inject
    private ParameterCBean client;

    @Inject
    private ParameterValuesCBean clientValues;

    @PostConstruct
    private void init() {
        param = client.getItem();
        values.addAll(client.getValues());
    }

    public List<ParameterConfigurationValues> getValues() {
        return values;
    }

    public void setValues(List<ParameterConfigurationValues> values) {
        this.values = values;
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

    public void onRowEdit(RowEditEvent event) {
        clientValues.setValue((ParameterConfigurationValues) event.getObject());
        clientValues.editItem();
    }

    public void onRowCancel(RowEditEvent event) {

    }

    public void onCellEdit(CellEditEvent event) {
        ParameterConfigurationValues vvv = values.get(event.getRowIndex());
        clientValues.editItem(vvv);
        values.clear();
        values.addAll(client.getValues());
    }

    public List<ParameterConfigurationValues> getSelectedValues() {
        return selectedValues;
    }

    public void setSelectedValues(List<ParameterConfigurationValues> selectedValues) {
        this.selectedValues = selectedValues;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

}
