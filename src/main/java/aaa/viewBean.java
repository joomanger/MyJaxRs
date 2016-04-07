package aaa;

import com.isd.myjaxrs.entity.Item;
import config.beans.ConfigClientBean;
import config.entity.ConfigurationLine;
import config.entity.ParameterConfiguration;
import config.entity.ParameterConfiguration.ParameterType;
import java.io.Serializable;
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
public class viewBean implements Serializable {

    @Inject
    private ConfigClientBean configClient;

    private Item item;
    private Integer lastVersion;

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

    public List<ConfigurationLine> getColumnNames() {
        try {
            return configClient.getLines(configClient.getItem(item.getId(), configClient.getLastVersion(getItem().getId())).getHeader_id());
        } catch (Exception ex) {
            return null;
        }
    }

}
