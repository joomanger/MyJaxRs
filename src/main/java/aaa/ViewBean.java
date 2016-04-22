package aaa;

import com.isd.myjaxrs.entity.Item;
import com.isd.myjaxrs.entity.SaleOrderLine;
import config.beans.ConfigClientBean;
import config.entity.Configuration;
import config.entity.ConfigurationLine;
import config.entity.ParameterConfiguration;
import config.entity.ParameterConfigurationValues;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.event.CellEditEvent;
import saleorder.beans.SaleOrderBean;
import saleorderline.beans.SaleOrderLineBean;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class ViewBean implements Serializable {

    @Inject
    private ConfigClientBean configClient;

    @Inject
    private SaleOrderBean sob;

    @Inject
    private SaleOrderLineBean slb;

    private Item item;
    private Integer lastVersion;

    private boolean disabledItem = false;

    private Long header_id;

    private List<ConfigurationLine> paramValues = new ArrayList<>();
    private List<SaleOrderLine> order_lines = new ArrayList<>();
    private List<SaleOrderLine> selected_lines = new ArrayList<>();
    private SaleOrderLine order_line = new SaleOrderLine();
    private Set<Integer> linesForSave = new HashSet<>();

    private ParameterConfigurationValues value;

    private List<ParameterConfiguration> parameters;

    private Boolean disableSave = true;

    private Map<String, OutputLabel> labels = new HashMap<>();
    private Map<String, InputText> texts = new HashMap<>();

    private OutputLabel outputLable = new OutputLabel();
    private InputText inputText = new InputText();

    @PostConstruct
    private void init() {
        updateListLines();

        labels.clear();
        texts.clear();
        for (ParameterConfiguration p : getAllLinesAttributes()) {
            if (p.getParameterType() != ParameterConfiguration.ParameterType.TABLE) {
                OutputLabel label = new OutputLabel();
                InputText text = new InputText();
                label.setValueExpression("value", getValueExpression("#{ord." + p.getAttribute().toLowerCase() + "}"));
                // setOutputLable(label);
                text.setValueExpression("value", getValueExpression("#{ord." + p.getAttribute().toLowerCase() + "}"));
                //setInputText(text);
                System.out.println(p.getAttribute() + " value=" + label.getValueExpression("value"));
                labels.put(p.getAttribute(), label);
                texts.put(p.getAttribute(), text);
            }
        }
        setParameters(getAllLinesAttributes());
        //(getAllLinesAttributes());
        //parameters=getAllLinesAttributes();
    }

    private void updateListLines() {
        try {
            this.order_lines = sob.getLines(1L);
        } catch (Exception ex) {

        }
    }

    public void onCellEdit(CellEditEvent event) {
        linesForSave.add(event.getRowIndex());
    }

    public void setHeader_id(Long header_id) {
        this.header_id = header_id;
    }

    public Long getHeader_id() {
        return header_id;
    }

    public ParameterConfigurationValues getValue() {
        return value;
    }

    public void setValue(ParameterConfigurationValues value) {
        this.value = value;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(Integer lastVersion) {
        this.lastVersion = lastVersion;
    }

    public List<ConfigurationLine> values() {
        try {
            return configClient.getLines(getConfiguration().getHeader_id());
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getLastConfigVersion() {
        return configClient.getLastVersion(getItem().getId());
    }

    public Configuration getConfiguration() {
        return configClient.getItem(item.getId(), getLastConfigVersion());
    }

    public List<ConfigurationLine> getParamValues() {
        return paramValues;
    }

    public void setParamValues(List<ConfigurationLine> paramValues) {
        this.paramValues = paramValues;
    }

    public boolean isDisabledItem() {
        return disabledItem;
    }

    public void setDisabledItem(boolean disabledItem) {
        this.disabledItem = disabledItem;
    }

    public String getClearURL() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }

    public List<SaleOrderLine> getOrder_lines() {
        return order_lines;
    }

    public void setOrder_lines(List<SaleOrderLine> order_lines) {
        this.order_lines = order_lines;
    }

    public List<SaleOrderLine> getSelected_lines() {
        return selected_lines;
    }

    public void setSelected_lines(List<SaleOrderLine> selected_lines) {
        this.selected_lines = selected_lines;
    }

    public SaleOrderLine getOrder_line() {
        return order_line;
    }

    public void setOrder_line(SaleOrderLine order_line) {
        this.order_line = order_line;
    }

    public void deleteLines() {
        Response t = null;
        for (SaleOrderLine line : selected_lines) {
            t = slb.deleteItem(line.getLine_id(), "Позиция " + line.getLine_num() + " удалена успешно");
        }
        order_lines.removeAll(selected_lines);
        selected_lines.clear();
        // setParameters(getAllLinesAttributes());

    }

    public List<ParameterConfiguration> getAllLinesAttributes() {
        List<ParameterConfiguration> list = new ArrayList<>();
        Set<String> attrs = new HashSet<>();
        for (SaleOrderLine line : getOrder_lines()) {
            Configuration config = configClient.getItem(line.getItem().getId(), line.getConfig_ver_num());
            for (ConfigurationLine configLine : configClient.getLines(config.getHeader_id())) {
                list.add(configLine.getParameter());
                attrs.add(configLine.getParameter().getAttribute());
            }
        }

        List<ParameterConfiguration> list2 = new ArrayList<>();
        for (String s : attrs) {
            for (ParameterConfiguration p : list) {
                if (p.getAttribute().equals(s)) {
                    list2.add(p);
                    break;
                }
            }
        }
        Collections.sort(list2);
        return list2;
    }

    public List<ParameterConfiguration> getParameters() {
        return parameters;
    }

    //Вывести в синглетон с стартапом
    public ValueExpression getValueExpression(String data) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = fc.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, data, Object.class);
        return valueExp;

    }

    public void setParameters(List<ParameterConfiguration> parameters) {
        this.parameters = parameters;
    }

    public OutputLabel getOutputLable() {
        System.out.println("get id=" + this.outputLable.getId());
        return labels.get(outputLable.getId());
    }

    public void setOutputLable(OutputLabel outputLable) {
        //this.outputLable = outputLable;
        labels.put(outputLable.getId(), outputLable);
        System.out.println("set id=" + this.outputLable.getId());
    }

    public InputText getInputText() {
        System.out.println(inputText.getId());
        return inputText;
    }

    public void setInputText(InputText inputText) {
        this.inputText = inputText;
    }

    public OutputLabel getMyOutputLabel(String attr) {
        return labels.get(attr);
    }

    public InputText getMyInputText(String attr) {
        return texts.get(attr);
    }

    public Boolean getDisableSave() {
        return disableSave;
    }

    public void setDisableSave(Boolean disableSave) {
        this.disableSave = disableSave;
    }

    public void saveLines() {
        String message = "Сохранено успешно";
        for (int a : linesForSave) {
            Response t = slb.editItem(order_lines.get(a), null);
            if (t.getStatus() != 204) {
                message = "Ошибка при сохранении: " + t.getStatusInfo().toString();
            }
        }
        slb.sendMessage(message);
        linesForSave.clear();
    }

    public Map<String, OutputLabel> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, OutputLabel> labels) {
        this.labels = labels;
    }
    
    public void Test(String t){
        System.out.println("t="+t);
    }

}
