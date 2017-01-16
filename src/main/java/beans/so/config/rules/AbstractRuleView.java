package beans.so.config.rules;

import service.AbstractView;

/**
 *
 * @author savin
 * @param <Rule>
 */
public abstract class AbstractRuleView<Rule> extends AbstractView<Rule> implements IRuleView<Rule> {

    private String formula;
    private short npp;
    private boolean isCreateFormula = true;
    private boolean isItemDisable = false;

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

    @Override
    public boolean isIsCreateFormula() {
        return isCreateFormula;
    }

    @Override
    public void setIsCreateFormula(boolean isCreateFormula) {
        this.isCreateFormula = isCreateFormula;
    }

    @Override
    public boolean isIsItemDisable() {
        return isItemDisable;
    }

    @Override
    public void setIsItemDisable(boolean isItemDisable) {
        this.isItemDisable = isItemDisable;
    }
}
