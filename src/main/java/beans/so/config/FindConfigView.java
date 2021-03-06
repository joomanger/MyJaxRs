package beans.so.config;

import entities.so.config.Configuration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class FindConfigView implements Serializable {

    @Inject
    private ConfigCBean clientBean;
    private List<Configuration> selectedConfigs;
    private List<Configuration> configs;
    

    @PostConstruct
    public void init() {
        selectedConfigs = new ArrayList<>();
        configs = new ArrayList<>();
        try{
        configs.addAll(clientBean.getItems());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<Configuration> getSelectedConfigs() {
        return selectedConfigs;
    }

    public void setSelectedConfigs(List<Configuration> selectedConfigs) {
        this.selectedConfigs = selectedConfigs;
    }

    public List<Configuration> getConfigs() {
        return configs;
    }

    public void setConfigs(List<Configuration> configs) {
        this.configs = configs;
    }

}
