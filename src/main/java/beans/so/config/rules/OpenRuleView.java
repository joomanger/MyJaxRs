package beans.so.config.rules;

import beans.so.config.ConfigCBean;
import entities.so.config.ConfigurationLine;
import entities.so.config.rules.Rule;
import entities.so.config.rules.RuleLine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenRuleView extends AbstractRuleView<Rule> {
    
    @Inject
    private RuleCBean client;
    
    @Inject
    private ConfigCBean configClient;
    
    private final Map<Long, List<ConfigurationLine>> cacheConfigItems = new HashMap<>();
    private final Map<String, String> param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    
    @PostConstruct
    @Override
    protected void init() {
        Rule r = client.find(Long.parseLong(param.get("header_id")));
        short npp = 0;
        for (RuleLine l : r.getLines()) {
            if (npp < l.getLine_number()) {
                npp = l.getLine_number();
            }
        }
        setNpp(npp);
        super.setEntity(r);
        setCacheConfigItems(r.getItem().getItem_id());
    }
    
    private void setCacheConfigItems(Long item_id) {
        this.setIsCreateFormula(false);
        if (!cacheConfigItems.containsKey(item_id) && item_id != null) {
            try {
                cacheConfigItems.put(item_id, configClient.getItem(item_id).getLines());
                
                System.out.println("setCacheConfigItems " + item_id);
            } catch (NullPointerException ex) {
                //throw new RuntimeException("Не определена конфигурация для данной позиции!");
                this.setIsCreateFormula(true);
                
            }
        }
    }
    
    public List<ConfigurationLine> getCacheConfigItems(Long item_id) {
        if (item_id != null) {
            return cacheConfigItems.get(item_id);
        }
        return null;
    }
}
