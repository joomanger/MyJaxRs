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

    @PostConstruct
    @Override
    protected void init() {
        super.setEntities(client.findAll());
    }
    
}
