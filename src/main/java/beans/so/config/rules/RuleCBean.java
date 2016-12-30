package beans.so.config.rules;

import beans.so.config.ConfigCBean;
import entities.so.config.ConfigurationLine;
import entities.so.config.rules.Rule;
import entities.so.config.rules.RuleLine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractClientBean2;
import service.AbstractEJB2;
import service.AbstractView;
import service.SessionActions;
import service.WhoIS;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class RuleCBean extends AbstractClientBean2<Rule> {

    @Inject
    private RuleEJB ejb;
    @Inject
    private ConfigCBean configClient;
    @Inject
    private SessionActions sa;
    @Inject
    private NewRuleView newView;
    @Inject
    private OpenRuleView openView;
    @Inject
    private FindRuleView findView;

    private Map<Long, List<ConfigurationLine>> cacheConfigItems = new HashMap<>();

    @Override
    protected AbstractEJB2<Rule> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<Rule> getOpenView() {
        return openView;
    }

    @Override
    protected AbstractView<Rule> getFindView() {
        return findView;
    }

    @Override
    protected AbstractView<Rule> getNewView() {
        return newView;
    }

    public void setCacheConfigItems(Long item_id) {
        System.out.println("setCacheConfigItems " + item_id);
        if (!cacheConfigItems.containsKey(item_id) && item_id != null) {
            try {
                cacheConfigItems.put(item_id, configClient.getItem(item_id).getLines());
            } catch (NullPointerException ex) {
                RuntimeException e = new RuntimeException("Не определена конфигурация для данной позиции!");
                throw e;
            }
        }
    }

    public List<ConfigurationLine> getCacheConfigItems(Long item_id) {
        if (item_id != null) {
            return cacheConfigItems.get(item_id);
        }
        return null;
    }

    public void addFormulaLine(IRuleView view) {
        RuleLine rl = new RuleLine();
        if(view.getFormula().isEmpty()){
            throw new RuntimeException("Формула не указана. Введите формулу!");
        }
        rl.setFormula(view.getFormula());
        short s = view.getNpp();
        view.setNpp(++s);
        rl.setLine_number(view.getNpp());
        if (rl instanceof WhoIS) {
            sa.createWHO(rl);
        }
        ((Rule) view.getEntity()).addLine(rl);
        view.setFormula(null);
    }

}
