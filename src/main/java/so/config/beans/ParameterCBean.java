package so.config.beans;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import so.config.entity.ParameterConfiguration;
import so.config.entity.ParameterConfigurationValues;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ParameterCBean {

    @Inject
    private NewParameterView npv;

    @Inject
    private FindParameterSession fpv;

    @Inject
    private OpenParameterView opv;
    
    @Inject 
    private FindParameterView pv;

    @Inject
    private ParameterEJB paramEJB;

    public ParameterConfiguration getItem() {
        //return super.getItem(ParameterConfiguration.class, fpv.getParamID());
        return paramEJB.find(fpv.getParamID());
    }

//    public ParameterConfiguration[] getItems() {
//        return super.getItems(ParameterConfiguration[].class);
//    }
    public List<ParameterConfiguration> getItems() {
        //return super.getItems(ParameterConfiguration[].class);
        return paramEJB.findAll();
    }

    public List<ParameterConfiguration> getItemsList() {
        //return Arrays.asList(super.getItems(ParameterConfiguration[].class));
        return paramEJB.findAll();
    }

    public List<ParameterConfigurationValues> getValues() {
//        return getTarget().path("/{header_id}/lines").resolveTemplate("header_id", fpv.getParamID()).request().get(new GenericType<List<ParameterConfigurationValues>>() {
//        });
        return paramEJB.getValues(fpv.getParamID());
    }

    public List<ParameterConfigurationValues> getValues(Long p_header_id) {
//        return getTarget().path("/{header_id}/lines").resolveTemplate("header_id", p_header_id).request().get(new GenericType<List<ParameterConfigurationValues>>() {
//        });
        return paramEJB.getValues(p_header_id);
    }

    public Integer getMaxLineNum() {
        // return getTarget().path("/{header_id}/max_line_num").resolveTemplate("header_id", fpv.getParamID()).request().get(Integer.class);
        return paramEJB.getMaxLineNum(fpv.getParamID());
    }

    public List<String> getAttrColumn() {
//        ParameterConfigurationAttrColumn obj=getTarget().path("/attrColumns").request().get(ParameterConfigurationAttrColumn.class);
//        return obj.getColumns();
        return paramEJB.getAttrColumns().getColumns();
    }

    public String editItem() {
        ParameterConfiguration pc = opv.getParam();
        //super.editItem(pc, "Параметр " + pc.getName() + " обновлен успешно");
        String status = paramEJB.create(pc);
        paramEJB.sendMessage(status, "Параметр " + pc.getName() + " обновлен успешно");
        return "params";
    }

    public String addItem() {
        ParameterConfiguration p = npv.getParamConfig();
        if (p.getParameterType() != ParameterConfiguration.ParameterType.TABLE) {
//            Response t = super.addItem(p, "Параметр " + p.getName() + " добавлен успешно");
            String status = paramEJB.create(p);
//            if (t.getStatus() != 200) {
            paramEJB.sendMessage(status, "Параметр " + p.getName() + " добавлен успешно");
            if (status.equals(paramEJB.SUCCESSFUL)) {
                return "params";
            } else {
                System.out.println("djopa!");
                return null;
            }
        } else {
            npv.setDisabledCB(true);
            return null;
        }
    }

    public String addItemTable() {
        ParameterConfiguration p = npv.getParamConfig();
        p.setValues(npv.getValues());
        // Response t = super.addItem(p, "Параметр " + p.getName() + " добавлен успешно");
        String status = paramEJB.create(p);
        paramEJB.sendMessage(status, "Параметр " + p.getName() + " добавлен успешно");
        if (status.equals(paramEJB.SUCCESSFUL)) {
            return "params";
        } else {
            System.out.println("djopa!");
            return null;
        }
    }
    
    public void deleteItems() {
        for (ParameterConfiguration p : pv.getParamsDelete()) {
            //pcb.deleteItem(p.getParameter_id(), "Параметр " + p.getName() + " удален успешно");
             String status = paramEJB.remove(p);
             paramEJB.sendMessage(status, "Параметр " + p.getName() + " удален успешно");
        }
        
        //params.clear();
        pv.getParams().clear();
        //params.addAll(pcb.getItemsList());
        pv.getParams().addAll(getItemsList());
        
        //paramsDelete.clear();
        pv.getParamsDelete().clear();
    }

}
