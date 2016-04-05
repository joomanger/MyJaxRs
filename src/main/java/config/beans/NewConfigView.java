package config.beans;

import config.entity.Configuration;
import config.entity.ConfigurationLine;
import config.entity.ParameterConfiguration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewConfigView implements Serializable {

    private Configuration configuration = new Configuration();
    private boolean disabledCB = false;

    private List<ConfigurationLine> selectedLines = new ArrayList<>();
    private List<ConfigurationLine> lines = new ArrayList<>();
    private ConfigurationLine line;
    private Boolean showLines = false;
    private int line_num = 0;
    private ParameterConfiguration paramConfig;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public boolean isDisabledCB() {
        return disabledCB;
    }

    public void setDisabledCB(boolean disabledCB) {
        this.disabledCB = disabledCB;
    }

    public List<ConfigurationLine> getSelectedLines() {
        return selectedLines;
    }

    public void setSelectedLines(List<ConfigurationLine> selectedLines) {
        this.selectedLines = selectedLines;
    }

    public Boolean getShowLines() {
        return showLines;
    }

    public void setShowLines(Boolean showLines) {
        this.showLines = showLines;
    }

    public List<ConfigurationLine> getLines() {
        return lines;
    }

    public void setLines(List<ConfigurationLine> lines) {
        this.lines = lines;
    }

    public ConfigurationLine getLine() {
        return line;
    }

    public void setLine(ConfigurationLine line) {
        this.line = line;
    }

    public void deleteItems() {
        System.out.println("deleting "+selectedLines.size());
        for (ConfigurationLine c : selectedLines) {
            System.out.println(c.getLine_num());
            lines.remove(c);
        }
        selectedLines.clear();
    }

    public ParameterConfiguration getParamConfig() {
        return paramConfig;
    }

    public void setParamConfig(ParameterConfiguration paramConfig) {
        this.paramConfig = paramConfig;
    }

    public void addParameter() {
        line_num++;
        ConfigurationLine line = new ConfigurationLine();
        line.setLine_num(line_num);
        line.setParameter(paramConfig);
        lines.add(line);
        this.setParamConfig(null);

    }

    public void addHeader() {
        setDisabledCB(true);
        setShowLines(true);
    }

}
