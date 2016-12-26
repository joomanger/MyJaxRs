package beans.so.config;

import entities.so.config.ParameterConfiguration;
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
