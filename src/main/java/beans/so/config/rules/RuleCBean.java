package beans.so.config.rules;

import entities.so.config.rules.Rule;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractClientBean2;
import service.AbstractEJB2;
import service.AbstractView;

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
    private NewRuleView newView;
    @Inject
    private OpenRuleView openView;
    @Inject
    private FindRuleView findView;
    
    
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

    
}
