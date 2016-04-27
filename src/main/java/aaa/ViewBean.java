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
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
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
    private List<ParameterConfiguration> list;
    private Set<String> attrs;
    private FacesContext fc;
    private Application app;
    private ExpressionFactory elFactory;
    private ELContext elContext;
    private List<String> selectedItems = new ArrayList<String>();

    private Map<String, Set<String>> paramMap = new HashMap<>();

    @PostConstruct
    private void init() {
        fc = FacesContext.getCurrentInstance();
        app = fc.getApplication();
        elFactory = app.getExpressionFactory();
        elContext = fc.getELContext();

        updateListLines();
        setParameters(getAllLinesAttributes());
    }

    public ValueExpression getValueExpression(String data) {
        return elFactory.createValueExpression(elContext, data, Object.class);
    }

    private void updateListLines() {
        try {
            this.order_lines = sob.getLines(1L);
        } catch (Exception ex) {

        }
    }

    public void onCellEdit(CellEditEvent event) {
        System.out.println("colKey=" + event.getColumn().getColumnKey());
        linesForSave.add(event.getRowIndex());
    }

    public void onRowEditInit(RowEditEvent event) {
        System.out.println("rowedit " + event.getObject());
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
        setParameters(getAllLinesAttributes());
    }

    public List<ParameterConfiguration> getAllItemAttributes() {
        try {
            list = new ArrayList<>();
            for (ConfigurationLine line : values()) {
                ParameterConfiguration p = line.getParameter();
                p.setAttribute(p.getAttribute().toLowerCase());
                list.add(p);
            }
            Collections.sort(list);
            return list;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<ParameterConfiguration> getAllLinesAttributes() {
        list = new ArrayList<>();
        attrs = new HashSet<>();
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
                    p.setAttribute(s.toLowerCase());
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

    public void setParameters(List<ParameterConfiguration> parameters) {
        this.parameters = parameters;
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

    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public void test(String attr) {
        if(selectedItems==null){
            paramMap.remove(attr);
        }
        if (selectedItems != null) {
            Set<String> st = new HashSet<>();
            for (String s : selectedItems) {
                st.add(s);
            }
            paramMap.put(attr, st);
        }

        
    }

    public Map<String, Set<String>> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Set<String>> paramMap) {
        this.paramMap = paramMap;
    }
    
    

}
