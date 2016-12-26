/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.so.saleorder;

import entities.item.Item;
import entities.so.SaleOrder;
import entities.so.SaleOrderLine;
import entities.so.config.Configuration;
import entities.so.config.ConfigurationLine;
import entities.so.config.ParameterConfiguration;
import entities.so.config.ParameterConfigurationValues;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.faces.view.ViewScoped;
import org.primefaces.event.CellEditEvent;

import org.primefaces.event.RowEditEvent;
import beans.so.config.ConfigCBean;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenSaleOrderView implements Serializable {

    @Inject
    private ConfigCBean configClient;

    @Inject
    private SaleOrderCBean sob;

    @Inject
    private FindSaleOrderBackingBean sessionBean;

    private Item item;
    private Integer lastVersion;
    private Long header_id;
    private List<ConfigurationLine> paramValues = new ArrayList<>();
    private List<SaleOrderLine> order_lines = new ArrayList<>();
    private List<SaleOrderLine> selected_lines = new ArrayList<>();
    private SaleOrderLine order_line = new SaleOrderLine();
    private Set<Integer> linesForSave = new HashSet<>();
    private ParameterConfigurationValues value;
    private List<ParameterConfiguration> parameters;
    private Boolean disableSave = true;
    private Boolean tableEditable = false;
    private SaleOrder order;
    private List<String> selectedItems = new ArrayList<>();

    private final Map<String, Boolean> editableCells = new HashMap<>();

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
        order = sob.getItem();
        updateListLines();
        setParameters(getAllLinesAttributes());
    }

    public SaleOrder getOrder() {
        return order;
    }

    public void setOrder(SaleOrder order) {
        this.order = order;
    }

    private void updateListLines() {
        try {
            this.order_lines = sob.getLines(sessionBean.getId());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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

    public List<ParameterConfiguration> getAllLinesAttributes() {
        try {
            List<ParameterConfiguration> list2 = new ArrayList<>();
            List<ConfigurationLine> cl = new ArrayList<>();

            Set<String> set = new LinkedHashSet<>();
            for (SaleOrderLine line : getOrder_lines()) {
                Configuration config = configClient.getItem(line.getItem().getItem_id(), configClient.getLastVersion(line.getItem().getItem_id()));
                for (ConfigurationLine configLine : configClient.getLines(config.getHeader_id())) {
                    ParameterConfiguration pc = configLine.getParameter();
                    pc.setAttribute(configLine.getParameter().getAttribute().toLowerCase());
                    cl.add(configLine);
                }
            }

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

    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public boolean isEditableCell(Long item_id, String attribute) {
        if (editableCells.containsKey(item_id + attribute)) {
            return editableCells.get(item_id + attribute);
        } else {
            boolean res = false;
            Configuration config = configClient.getItem(item_id, configClient.getLastVersion(item_id));
            for (ConfigurationLine cl : configClient.getLines(config.getHeader_id())) {
                if (cl.getParameter().getAttribute().equals(attribute.toUpperCase())) {
                    res = true;
                }
            }
            editableCells.put(item_id + attribute, res);
            return res;
        }

    }

    public Set<Integer> getLinesForSave() {
        return linesForSave;
    }

    public void setLinesForSave(Set<Integer> linesForSave) {
        this.linesForSave = linesForSave;
    }

    public Boolean getTableEditable() {
        return tableEditable;
    }

    public void setTableEditable(Boolean tableEditable) {
        this.tableEditable = tableEditable;
    }

}
