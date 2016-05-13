package so.config.beans;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import so.config.entity.ParameterConfiguration;
import so.config.entity.ParameterConfigurationValues;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ParameterCBean {

    @Inject
    private NewParameterView npv;

    @Inject
    private FindParameterSession fpv;

    @Inject
    private OpenParameterView opv;

    @Inject
    private FindParameterView pv;

    @Inject
    private ParameterEJB paramEJB;

    @Inject
    private ParameterValuesCBean clientValues;

    public ParameterConfiguration getItem() {
        return paramEJB.find(fpv.getParamID());
    }

    public List<ParameterConfiguration> getItems() {
        return paramEJB.findAll();
    }

    public List<ParameterConfiguration> getItemsList() {
        return paramEJB.findAll();
    }

    public List<ParameterConfigurationValues> getValues() {
        return paramEJB.getValues(fpv.getParamID());
    }

    public List<ParameterConfigurationValues> getValues(Long p_header_id) {
        return paramEJB.getValues(p_header_id);
    }

    public Integer getMaxLineNum() {
        return paramEJB.getMaxLineNum(fpv.getParamID());
    }

    public List<String> getAttrColumn() {
        return paramEJB.getAttrColumns().getColumns();
    }

    public String editItem() {
        ParameterConfiguration pc = opv.getParam();
        String status = paramEJB.create(pc);
        paramEJB.sendMessage(status, "Параметр " + pc.getName() + " обновлен успешно");
        return "params";
    }

    public String addItem() {
        ParameterConfiguration p = npv.getParamConfig();
        if (p.getParameterType() != ParameterConfiguration.ParameterType.TABLE) {
            String status = paramEJB.create(p);
            paramEJB.sendMessage(status, "Параметр " + p.getName() + " добавлен успешно");
            if (status.equals(paramEJB.SUCCESSFUL)) {
                return "params";
            } else {
                System.out.println("djopa!");
                return null;
            }
        } else {
            npv.setDisabledCB(true);
            return null;
        }
    }

    public String addItemTable() {
        ParameterConfiguration p = npv.getParamConfig();
        p.setValues(npv.getValues());
        String status = paramEJB.create(p);
        paramEJB.sendMessage(status, "Параметр " + p.getName() + " добавлен успешно");
        if (status.equals(paramEJB.SUCCESSFUL)) {
            return "params";
        } else {
            System.out.println("djopa!");
            return null;
        }
    }

    public void deleteItems() {
        for (ParameterConfiguration p : pv.getParamsDelete()) {
            String status = paramEJB.remove(p);
            paramEJB.sendMessage(status, "Параметр " + p.getName() + " удален успешно");
        }

        pv.getParams().clear();
        pv.getParams().addAll(getItemsList());
        pv.getParamsDelete().clear();
    }

    /*NewParameterView methods:*/
    public void deleteParameterValues() {
        for (ParameterConfigurationValues p : npv.getSelectedValues()) {
            npv.getValues().remove(p);
        }
        npv.getSelectedValues().clear();
    }

    public void addParameterValue() {
        if (!npv.getParameterValue().trim().isEmpty()) {
            int l = npv.getLine_num();
            l++;
            npv.setLine_num(l);
            ParameterConfigurationValues pcv = new ParameterConfigurationValues();
            pcv.setLine_num(l);
            pcv.setParameterValue(npv.getParameterValue());
            npv.getValues().add(pcv);
            npv.setParameterValue(null);
        }
    }

    /*OpenParameterView methods:*/
    public void addNewValue() {
        ParameterConfigurationValues p = new ParameterConfigurationValues();
        p.setParameter_id(opv.getParam().getParameter_id());
        p.setLine_num(getMaxLineNum() + 1);
        p.setParameterValue(opv.getNewValue());
        clientValues.addItem(p);
        opv.getValues().clear();
        opv.getValues().addAll(getValues());
        opv.setNewValue(null);
    }

    public void deleteValues() {
        for (ParameterConfigurationValues p : opv.getSelectedValues()) {
            clientValues.deleteItem(p.getParamater_value_id());
        }
        opv.getValues().clear();
        opv.getValues().addAll(getValues());
        opv.getSelectedValues().clear();
    }
}
