package aaa;

import com.isd.myjaxrs.entity.Item;
import config.beans.ConfigClientBean;
import config.entity.ConfigurationLine;
import config.entity.ParameterConfigurationValues;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class ViewBean implements Serializable {

    @Inject
    private ConfigClientBean configClient;

    private Item item;
    private Integer lastVersion;

    private Long header_id;

    private List<ConfigurationLine> paramValues = new ArrayList<>();

    private ParameterConfigurationValues value;

    public void setHeader_id(Long header_id) {
        //System.out.println("viewBean:header_id=" + header_id);
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
        ///paramValues=null;
        try {
           return configClient.getLines(configClient.getItem(item.getId(), configClient.getLastVersion(getItem().getId())).getHeader_id());
        } catch (Exception e) {
           return null;
        }
        //return paramValues;
    }

    public List<ConfigurationLine> getParamValues() {
        return paramValues;
    }

    public void setParamValues(List<ConfigurationLine> paramValues) {
        this.paramValues = paramValues;
    }


}
