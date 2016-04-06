package config.beans;

import config.entity.Configuration;
import config.entity.ConfigurationLine;
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
public class OpenConfigView implements Serializable {
    
    @Inject
    private ConfigClientBean client;
    
    @Inject
    private ConfigLineClientBean clientLine;
    
    private Configuration configuration = new Configuration();
    private List<ConfigurationLine> lines = new ArrayList<>();
    private List<ConfigurationLine> selectedLines = new ArrayList<>();
    private Integer lastLineNum;
    
    private ParameterConfiguration paramConfig;
    
    @PostConstruct
    private void init() {
        configuration = client.getItem();
        lines.addAll(client.getLines());
        lastLineNum = client.getMaxLineNum();
    }
    
    public Configuration getConfiguration() {
        return configuration;
    }
    
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    
    public List<ConfigurationLine> getLines() {
        return lines;
    }
    
    public void setLines(List<ConfigurationLine> lines) {
        this.lines = lines;
    }
    
    public List<ConfigurationLine> getSelectedLines() {
        return selectedLines;
    }
    
    public void setSelectedLines(List<ConfigurationLine> selectedLines) {
        this.selectedLines = selectedLines;
    }
    
    public ParameterConfiguration getParamConfig() {
        return paramConfig;
    }
    
    public void setParamConfig(ParameterConfiguration paramConfig) {
        this.paramConfig = paramConfig;
    }
    
    public void addItem() {
        ConfigurationLine line = new ConfigurationLine();
        line.setParameter(paramConfig);
        line.setLine_num(++lastLineNum);
        line.setHeader_id(configuration.getHeader_id());
        lines.add(line);
        setParamConfig(null);
    }
    
    public void deleteItems() {
        for (ConfigurationLine line : selectedLines) {
//            clientLine.deleteItem(line.getLine_id(), "Строка " + line.getLine_num() + " удалена успешно");
            lines.remove(line);
        }
//        lines.clear();
//        lines.addAll(client.getLines());
        selectedLines.clear();
    }
    
    public String newVersion() {
        Configuration c = getConfiguration();
        System.out.println(configuration.getDescription());
        c.setHeader_id(null);
        c.setConfig_ver_num(client.getLastVersion(c.getItem().getId()) + 1);
        List<ConfigurationLine> newLines = new ArrayList<>();
        for (ConfigurationLine l : lines) {
            l.setLine_id(null);
            l.setHeader_id(null);
            newLines.add(l);
        }
        c.setLines(newLines);
        client.addItem(c, "Новая версия конфигуации сохранена");
        return "configs";
    }
}
