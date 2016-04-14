package config.beans;

import config.entity.Configuration;
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
public class FindConfigView implements Serializable {

    @Inject
    private ConfigClientBean clientBean;
    private List<Configuration> selectedConfigs;
    private List<Configuration> configs;

    @PostConstruct
    public void init() {
        selectedConfigs = new ArrayList<>();
        configs = new ArrayList<>();
        configs.addAll(clientBean.getItemsList());
    }

    public List<Configuration> getSelectedConfigs() {
        return selectedConfigs;
    }

    public void setSelectedConfigs(List<Configuration> selectedConfigs) {
        this.selectedConfigs = selectedConfigs;
    }

    public List<Configuration> getConfigs() {
        return configs;
    }

    public void setConfigs(List<Configuration> configs) {
        this.configs = configs;
    }

    public void deleteConfigs() {
        for (Configuration c : selectedConfigs) {
            clientBean.deleteItem(c.getHeader_id(), "Конфигурация " + c.getItem().getName() + ":" + c.getConfig_ver_num() + " удалена успешно");
        }
        selectedConfigs.clear();
        configs.clear();
        configs.addAll(clientBean.getItemsList());
    }

}