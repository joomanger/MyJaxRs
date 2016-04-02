package config.beans;

import config.entity.ParameterConfiguration;
import config.entity.ParameterConfigurationValues;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private ParameterClientBean client;

    @Inject
    private ParameterValuesClientBean clientValues;

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
        //Object oldValue = event.getOldValue();
        System.out.println("config.beans.OpenParameterView.onCellEdit() " + event.getRowIndex());
        ParameterConfigurationValues vvv = values.get(event.getRowIndex());
        clientValues.editItem(vvv, "Строка обновлена");
        values.clear();
        values.addAll(client.getValues());
    }

    public List<ParameterConfigurationValues> getSelectedValues() {
        return selectedValues;
    }

    public void setSelectedValues(List<ParameterConfigurationValues> selectedValues) {
        this.selectedValues = selectedValues;
    }

    public void deleteItems() {
        for (ParameterConfigurationValues p : selectedValues) {
            clientValues.deleteItem(p.getParamater_value_id(), "Параметр " + p.getParameterValue() + " удален успешно");
        }
        values.clear();
        values.addAll(client.getValues());
        selectedValues.clear();
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public void addNewValue() {
        ParameterConfigurationValues p = new ParameterConfigurationValues();
        p.setParameter_id(param.getParameter_id());
        p.setLine_num(client.getMaxLineNum() + 1);
        p.setParameterValue(newValue);
        clientValues.addItem(p, "Параметр добавлен успешно");
        values.clear();
        values.addAll(client.getValues());
        newValue = null;
    }

}
