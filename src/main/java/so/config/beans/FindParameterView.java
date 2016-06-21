package so.config.beans;

import so.config.entity.ParameterConfiguration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private ParameterCBean pcb;
   //private ParameterClientBean pcb;
    private List<ParameterConfiguration> paramsDelete;
    private List<ParameterConfiguration> params;

    @PostConstruct
    public void init() {
        paramsDelete = new ArrayList<>();
        params = new ArrayList<>();
        try{
        params.addAll(pcb.getItemsList());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<ParameterConfiguration> getParamsDelete() {
        return paramsDelete;
    }

    public void setParamsDelete(List<ParameterConfiguration> paramsDelete) {
        this.paramsDelete = paramsDelete;
    }

    public List<ParameterConfiguration> getParams() {
        return params;
    }

    public void setParams(List<ParameterConfiguration> params) {
        this.params = params;
    }

    

}
