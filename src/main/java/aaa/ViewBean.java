package aaa;

import com.isd.myjaxrs.entity.Item;
import com.isd.myjaxrs.entity.SaleOrderLine;
import config.beans.ConfigClientBean;
import config.entity.Configuration;
import config.entity.ConfigurationLine;
import config.entity.ParameterConfigurationValues;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
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

    private ParameterConfigurationValues value;

    @PostConstruct
    private void init() {
        updateListLines();
    }

    private void updateListLines() {
        try {
            this.order_lines = sob.getLines(1L);
        } catch (Exception ex) {

        }
    }

    public void onRowEdit(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Строка изменена успешно");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
        slb.editItem((SaleOrderLine) event.getObject(), "ухты");
        updateListLines();
    }

    public void onRowCancel(RowEditEvent event) {
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

//    public List<SaleOrderLine> order_lines() {
//        try {
//            return sob.getLines(1L);
//        } catch (Exception e) {
//            return null;
//        }
//    }
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

    }

}
