package beans.so.config.rules;

import entities.so.config.rules.Rule;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewRuleView extends AbstractView<Rule> {
    
    
    
    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(new Rule());
    }

}
