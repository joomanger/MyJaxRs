package beans.so.config;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entities.so.config.ParameterConfigurationValues;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ParameterValuesCBean {

    @Inject
    private ParameterValuesEJB paramEJB;

    private ParameterConfigurationValues value;

    public ParameterConfigurationValues getValue() {
        return value;
    }

    public void setValue(ParameterConfigurationValues value) {
        this.value = value;
    }

    public void editItem() {
        String status = paramEJB.edit(value);
        paramEJB.sendMessage(status, "Параметр обновлен успешно");
    }

    public void editItem(ParameterConfigurationValues v) {
        String status = paramEJB.edit(v);
        paramEJB.sendMessage(status, "Строка обновлена");

    }

    public ParameterConfigurationValues getValue(Long p_header_id) {
        return paramEJB.find(p_header_id);
    }

    public void deleteItem(Long id) {
        ParameterConfigurationValues pv = paramEJB.find(id);
        String status = paramEJB.remove(pv);
        paramEJB.sendMessage(status, "Параметр " + pv.getParameterValue() + " удален успешно");
    }

    public void addItem(ParameterConfigurationValues pv) {
        String status = paramEJB.create(pv);
        paramEJB.sendMessage(status, "Параметр добавлен успешно");
    }

}
