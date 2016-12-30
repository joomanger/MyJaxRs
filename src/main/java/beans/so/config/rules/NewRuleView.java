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
public class NewRuleView extends AbstractView<Rule> implements IRuleView<Rule>{

    private String formula;
    private short npp;

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
    
}
