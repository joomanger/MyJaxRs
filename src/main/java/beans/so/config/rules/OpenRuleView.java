package beans.so.config.rules;

import entities.so.config.rules.Rule;
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
public class OpenRuleView extends AbstractView<Rule> {

    @Inject
    private RuleCBean client;

    @Inject
    private RuleConversation rc;

    private String formula;

    @PostConstruct
    @Override
    protected void init() {
        System.out.println("rc="+rc.getRule_id());
        super.setEntity(client.find(rc.getRule_id()));
        rc.endConversation();
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

}
