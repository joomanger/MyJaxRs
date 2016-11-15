package so.config.beans;

import so.config.entity.Configuration;
import so.config.entity.ConfigurationLine;
import so.config.entity.ParameterConfiguration;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import service.AbstractView;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class NewConfigView extends AbstractView<Configuration> {

    private final Configuration entity = new Configuration();
    private boolean disabledCB;
    private ConfigurationLine line;
    private boolean showLines;
    private int line_num = 0;
    private ParameterConfiguration paramConfig;

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(entity);
    }

    public int getLine_num() {
        return line_num;
    }

    public void setLine_num(int line_num) {
        this.line_num = line_num;
    }

    public boolean isDisabledCB() {
        return disabledCB;
    }

    public void setDisabledCB(boolean disabledCB) {
        this.disabledCB = disabledCB;
    }

    public boolean getShowLines() {
        return showLines;
    }

    public void setShowLines(boolean showLines) {
        this.showLines = showLines;
    }

    public ConfigurationLine getLine() {
        return line;
    }

    public void setLine(ConfigurationLine line) {
        this.line = line;
    }

//    public void deleteItems() {
//        System.out.println("deleting " + selectedLines.size());
//        for (ConfigurationLine c : selectedLines) {
//            System.out.println(c.getLine_num());
//            lines.remove(c);
//        }
//        selectedLines.clear();
//    }
    public ParameterConfiguration getParamConfig() {
        return paramConfig;
    }

    public void setParamConfig(ParameterConfiguration paramConfig) {
        this.paramConfig = paramConfig;
    }

//    public void addParameter() {
//        if (!paramConfig.getName().isEmpty()) {
//            line_num++;
//            ConfigurationLine line = new ConfigurationLine();
//            line.setLine_num(line_num);
//            line.setParameter(paramConfig);
//            lines.add(line);
//            this.setParamConfig(null);
//        }
//
//    }
    public void addHeader() {
        setDisabledCB(true);
        setShowLines(true);
    }

}
