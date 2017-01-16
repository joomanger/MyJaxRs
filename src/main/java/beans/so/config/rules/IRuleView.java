package beans.so.config.rules;

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
}
