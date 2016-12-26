package beans.so.config;

import entities.so.config.Configuration;
import entities.so.config.ConfigurationLine;
import entities.so.config.ParameterConfiguration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class OpenConfigView implements Serializable {

    @Inject
    private ConfigCBean client;

//    @Inject
//    private ConfigLineCBean clientLine;

    private Configuration configuration = new Configuration();
    private List<ConfigurationLine> lines = new ArrayList<>();
    private List<ConfigurationLine> selectedLines = new ArrayList<>();
    private Set<Integer> linesForSave = new HashSet<>();
    private Integer lastLineNum;

    private ParameterConfiguration paramConfig;
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
       try{ 
        configuration = client.getItem();
        lines.addAll(client.getLines());
        lastLineNum = client.getMaxLineNum();
       }catch(Exception ex){
           System.out.println(ex.getMessage());
       }
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

//    public void addItem() {
//        ConfigurationLine line = new ConfigurationLine();
//        line.setParameter(paramConfig);
//        line.setLine_num(++lastLineNum);
//        line.setHeader_id(configuration.getHeader_id());
//        lines.add(line);
//        setParamConfig(null);
//    }

//    public void deleteItems() {
//        for (ConfigurationLine line : selectedLines) {
////            clientLine.deleteItem(line.getLine_id(), "Строка " + line.getLine_num() + " удалена успешно");
//            lines.remove(line);
//        }
////        lines.clear();
////        lines.addAll(client.getLines());
//        selectedLines.clear();
//    }

    public void onCellEdit(CellEditEvent event) {
        linesForSave.add(event.getRowIndex());
    }

//    public String newVersion() {
//        Configuration c = getConfiguration();
//        // System.out.println(configuration.getDescription());
//        c.setHeader_id(null);
//        c.setConfig_ver_num(client.getLastVersion(c.getItem().getId()) + 1);
//        List<ConfigurationLine> newLines = new ArrayList<>();
//        for (ConfigurationLine l : lines) {
//            l.setLine_id(null);
//            l.setHeader_id(null);
//            newLines.add(l);
//        }
//        c.setLines(newLines);
//        client.addItem(c, "Новая версия конфигуации сохранена");
//        return "configs";
//    }

//    public void currVersion() {
//        Configuration c = getConfiguration();
//        c.setLines(lines);
//        client.editItem(c, null);
//        String message = "Конфигурация сохранена";
//        for (int a : linesForSave) {
//            ConfigurationLine l = lines.get(a);
//            Response t = clientLine.editItem(l, null);
//            if (t.getStatus() != 204) {
//                message = "Ошибка при сохранении: " + t.getStatusInfo().toString();
//            }
//        }
//        clientLine.sendMessage(message);
//        linesForSave.clear();
//        lines.clear();
//        lines.addAll(client.getLines());
//
//    }

    public Set<Integer> getLinesForSave() {
        return linesForSave;
    }

    public void setLinesForSave(Set<Integer> linesForSave) {
        this.linesForSave = linesForSave;
    }

    public Integer getLastLineNum() {
        return lastLineNum;
    }

    public void setLastLineNum(Integer lastLineNum) {
        this.lastLineNum = lastLineNum;
    }
    
    
    
}
