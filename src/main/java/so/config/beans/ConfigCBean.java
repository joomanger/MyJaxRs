package so.config.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import so.config.entity.Configuration;
import so.config.entity.ConfigurationLine;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ConfigCBean implements Serializable {

    @Inject
    private NewConfigView newView;

    @Inject
    private FindConfigSession findSession;

    @Inject
    private OpenConfigView openView;

    @Inject
    private FindConfigView fcv;

    @Inject
    private ConfigEJB configEJB;

    @Inject
    private ConfigLineEJB lineEJB;

    public Configuration getItem() {
        return configEJB.find(findSession.getConfig_id());
    }

    public Configuration getItem(Long p_item_id, Integer p_ver_num) {
        return configEJB.getConfig(p_item_id, p_ver_num);
    }

    public List<Configuration> getItems() {
        return configEJB.findAll();
    }

    public List<ConfigurationLine> getLines() {
        return configEJB.getValues(findSession.getConfig_id());
    }

    public List<ConfigurationLine> getLines(Long p_config_id) {
        return configEJB.getValues(p_config_id);
    }

    public Integer getMaxLineNum() {
        return configEJB.getMaxLineNum(findSession.getConfig_id());
    }

    public Integer getMaxLineNum(Long p_config_id) {
        return configEJB.getMaxLineNum(p_config_id);
    }

    public String addItemTable() {
        Configuration config = newView.getEntity();
        config.setConfig_ver_num(getLastVersion() + 1);
        String status = configEJB.edit(config);
        configEJB.sendMessage(status, "Конфигурация сохранена успешно");
        return "configs";
    }

    public Integer getLastVersion() {
        Integer version = configEJB.getLastVersion(newView.getEntity().getItem().getItem_id());
        if (version == null) {
            return 0;
        } else {
            return version;
        }
    }

    public Integer getLastVersion(Long p_item_id) {
        if (p_item_id != null) {
            Integer version = configEJB.getLastVersion(p_item_id);
            if (version == null) {
                return 0;
            } else {
                return version;
            }
        } else {
            return 0;
        }
    }

    //FindConfigView methods:
    public void deleteConfigs() {
        for (Configuration c : fcv.getSelectedConfigs()) {
            String status = configEJB.remove(c);
            configEJB.sendMessage(status, "Конфигурация " + c.getItem().getName() + ":" + c.getConfig_ver_num() + " удалена успешно");
            //clientBean.deleteItem(c.getHeader_id(), "Конфигурация " + c.getItem().getName() + ":" + c.getConfig_ver_num() + " удалена успешно");
        }
        fcv.getSelectedConfigs().clear();
        fcv.getConfigs().clear();
        fcv.getConfigs().addAll(getItems());
    }

    //NewConfigView methods:
    public void addParameter() {
        if (newView.getParamConfig() != null) {
            int l = newView.getLine_num();
            l++;
            newView.setLine_num(l);
            ConfigurationLine line = new ConfigurationLine();
            line.setLine_num(l);
            line.setParameter(newView.getParamConfig());
            newView.getEntity().addLine(line);
            newView.setParamConfig(null);
        }
    }

    public void deleteItems() {
        for (Object c : newView.getSelectedEntityLines()) {
            if (c instanceof ConfigurationLine) {
                newView.getEntity().getLines().remove((ConfigurationLine) c);
            }
        }
        newView.getSelectedEntityLines().clear();
    }

    //OpenConfigView methods:
    public void addItemOCV() {
        ConfigurationLine line = new ConfigurationLine();
        line.setParameter(openView.getParamConfig());
        int l = openView.getLastLineNum();
        openView.setLastLineNum(l);
        line.setLine_num(++l);
        //line.setHeader_id(openView.getConfiguration().getHeader_id());
        openView.getLines().add(line);
        openView.setParamConfig(null);
    }

    public void deleteItemsOCV() {
        for (ConfigurationLine line : openView.getSelectedLines()) {
            openView.getLines().remove(line);
        }
        openView.getSelectedLines().clear();
    }

    public String newVersionOCV() {
        Configuration c = openView.getConfiguration();
        c.setHeader_id(null);
        c.setConfig_ver_num(getLastVersion(c.getItem().getItem_id()) + 1);
        List<ConfigurationLine> newLines = new ArrayList<>();
        for (ConfigurationLine l : openView.getLines()) {
            l.setLine_id(null);
            //  l.setHeader_id(null);
            newLines.add(l);
        }
        c.setLines(newLines);
        String status = configEJB.create(c);
        configEJB.sendMessage(status, "Новая версия конфигуации сохранена");
        return "configs";
    }

    public void currVersionOCV() {
        Configuration c = openView.getConfiguration();
        c.setLines(openView.getLines());
        String status = configEJB.edit(c);
        for (int a : openView.getLinesForSave()) {
            ConfigurationLine l = openView.getLines().get(a);
            lineEJB.edit(l);
        }
        configEJB.sendMessage(status, "Конфигурация сохранена");
        openView.getLinesForSave().clear();
        openView.getLines().clear();
        openView.getLines().addAll(getLines());

    }

}
