package config.beans;

import config.entity.ParameterConfiguration;
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
public class FindParameterView implements Serializable {

    @Inject
    private ParameterClientBean pcb;
    
    private List<ParameterConfiguration> params;
    
    private ParameterConfiguration param;
    
    public ParameterConfiguration getParam() {
        return param;
    }
    
    public void setParam(ParameterConfiguration param) {
        this.param = param;
    }
    
    public List<ParameterConfiguration> getParams() {
        return params;
    }
    
    public void setParams(List<ParameterConfiguration> params) {
        this.params = params;
    }
    
    public void deleteItems() {
        for (ParameterConfiguration p : params) {
            System.out.println(p.getName());
            pcb.deleteItem(p.getParameter_id(),"Параметр удален успешно");
        }
        params.clear();
    }
    
}
