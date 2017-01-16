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
public class FindRuleView extends AbstractView<Rule> {

    @Inject
    private RuleCBean client;

    private Long rule_id;

    @PostConstruct
    @Override
    protected void init() {
        super.setEntities(client.findAll());
    }

    public Long getRule_id() {
        return rule_id;
    }

    public void setRule_id(Long rule_id) {
        this.rule_id = rule_id;
    }

}
