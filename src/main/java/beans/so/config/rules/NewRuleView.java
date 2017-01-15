package beans.so.config.rules;

import beans.so.config.ConfigCBean;
import entities.so.config.ConfigurationLine;
import entities.so.config.rules.Rule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewRuleView extends AbstractView<Rule> implements IRuleView<Rule> {

    private String formula;
    private short npp;
    @Inject
    private ConfigCBean configClient;

    private final Map<Long, List<ConfigurationLine>> cacheConfigItems = new HashMap<>();

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(new Rule());
    }

    @Override
    public String getFormula() {
        return formula;
    }

    @Override
    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Override
    public short getNpp() {
        return npp;
    }

    @Override
    public void setNpp(short npp) {
        this.npp = npp;
    }

    public void setCacheConfigItems(Long item_id) {

        if (!cacheConfigItems.containsKey(item_id) && item_id != null) {
            try {
                cacheConfigItems.put(item_id, configClient.getItem(item_id).getLines());
                System.out.println("setCacheConfigItems " + item_id);
            } catch (NullPointerException ex) {
                throw new RuntimeException("Не определена конфигурация для данной позиции!");
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
