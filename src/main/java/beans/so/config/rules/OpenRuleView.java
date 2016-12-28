package beans.so.config.rules;

import entities.so.config.rules.Rule;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenRuleView extends AbstractView<Rule> {

    @Override
    protected void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
