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
import java.util.LinkedHashSet;
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
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import saleorder.beans.FindSaleOrderBackingBean;
import saleorder.beans.SaleOrderCBean;
import saleorderline.beans.SaleOrderLineCBean;

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
    private SaleOrderCBean sob;

    @Inject
    private SaleOrderLineCBean slb;
    
    @Inject
    private FindSaleOrderBackingBean sessionBean;

    private Item item;
    private Integer lastVersion;
    private Long header_id;
    private List<ConfigurationLine> paramValues = new ArrayList<>();
    private List<SaleOrderLine> order_lines = new ArrayList<>();
    private List<SaleOrderLine> selected_lines = new ArrayList<>();
    private SaleOrderLine order_line = new SaleOrderLine();
    private final Set<Integer> linesForSave = new HashSet<>();
    private ParameterConfigurationValues value;
    private List<ParameterConfiguration> parameters;
    private Boolean disableSave = true;
    private FacesContext fc;
    private Application app;
    private ExpressionFactory elFactory;
    private ELContext elContext;
    private List<String> selectedItems = new ArrayList<>();

    private final Map<String, Boolean> editableCells = new HashMap<>();

    //карта мульти параметров со значениями
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
            this.order_lines = sob.getLines(sessionBean.getId());
        } catch (Exception ex) {

        }
    }

    public void onCellEdit(CellEditEvent event) {
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

    public Integer getLastConfigVersion(Long p_item_id) {
        return configClient.getLastVersion(p_item_id);
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
        for (SaleOrderLine line : selected_lines) {
            slb.delete(line.getLine_id(), "Позиция " + line.getLine_num() + " удалена успешно");
        }
        order_lines.removeAll(selected_lines);
        selected_lines.clear();
        updateListLines();
        setParameters(getAllLinesAttributes());
    }

    private List<ParameterConfiguration> getAllLinesAttributes() {
        try {
            List<ParameterConfiguration> list2 = new ArrayList<>();
            List<ConfigurationLine> cl = new ArrayList<>();

            Set<String> set = new LinkedHashSet<>();
            for (SaleOrderLine line : getOrder_lines()) {
                Configuration config = configClient.getItem(line.getItem().getId(), getLastConfigVersion(line.getItem().getId()));
                for (ConfigurationLine configLine : configClient.getLines(config.getHeader_id())) {
                    ParameterConfiguration pc = configLine.getParameter();
                    pc.setAttribute(configLine.getParameter().getAttribute().toLowerCase());
                    cl.add(configLine);
                }
            }

            Collections.sort(cl);
            for (ConfigurationLine l : cl) {
                set.add(l.getParameter().getAttribute());
            }
            for (String l : set) {
                for (ConfigurationLine ll : cl) {
                    if (ll.getParameter().getAttribute().equals(l)) {
                        list2.add(ll.getParameter());
                        break;
                    }
                }
            }
            return list2;
        } catch (Exception ex) {
            return null;
        }
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
        for (int a : linesForSave) {
            SaleOrderLine sl = order_lines.get(a);
            sl.setConfig_ver_num(getLastConfigVersion(sl.getItem().getId()));
            slb.edit(sl, "Строка " + sl.getLine_num() + " сохранена успешно");
        }
        linesForSave.clear();
    }

    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public void test(String attr) {
        if (selectedItems == null) {
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

    public void addLine() {
        SaleOrderLine line = new SaleOrderLine();
        line.setHeader_id(sessionBean.getId());
        line.setItem(getItem());
        Short next_num = sob.getMaxLineNum(sessionBean.getId());
        next_num++;
        line.setLine_num(next_num);
        line.setConfig_ver_num(getLastConfigVersion());
        slb.create(line, "Строка успешно добавлена");
        updateListLines();
        setParameters(getAllLinesAttributes());
    }

    public boolean isEditableCell(Long item_id, String attribute) {
        if (editableCells.containsKey(item_id + attribute)) {
            return editableCells.get(item_id + attribute);
        } else {
            boolean res = false;
            Configuration config = configClient.getItem(item_id, getLastConfigVersion(item_id));

            for (ConfigurationLine cl : configClient.getLines(config.getHeader_id())) {
                if (cl.getParameter().getAttribute().equals(attribute.toUpperCase())) {
                    res = true;
                }
            }
            editableCells.put(item_id + attribute, res);
            return res;
        }

    }
}
