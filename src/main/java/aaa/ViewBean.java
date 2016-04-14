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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import saleorder.beans.SaleOrderBean;

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

    private Item item;
    private Integer lastVersion;

    private boolean disabledItem = false;

    private Long header_id;

    private List<ConfigurationLine> paramValues = new ArrayList<>();
    private List<SaleOrderLine> order_lines = new ArrayList<>();

    private ParameterConfigurationValues value;

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

    public List<SaleOrderLine> order_lines() {
        try {
            return sob.getLines(1L);
        } catch (Exception e) {
            return null;
        }
    }

}
