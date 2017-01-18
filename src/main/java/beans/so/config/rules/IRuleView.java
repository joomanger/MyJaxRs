package beans.so.config.rules;

import entities.so.config.rules.RuleLine;
import service.IView;

/**
 *
 * @author savin
 * @param <T>
 */
public interface IRuleView<T> extends IView<T> {

    String getFormula();

    void setFormula(String formula);

    short getNpp();

    void setNpp(short p);

    boolean isIsCreateFormula();

    void setIsCreateFormula(boolean isCreateFormula);

    boolean isIsItemDisable();

    void setIsItemDisable(boolean isItemDisable);

    RuleLine getEditedRuleLine();

    void setEditedRuleLine(RuleLine editedRuleLine);
    
    void setRuleLineForEditor(RuleLine line);
}
