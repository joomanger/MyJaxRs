package beans.so.config.rules;

import service.IView;

/**
 *
 * @author savin
 */
public interface IRuleView<T> extends IView<T> {

    String getFormula();

    void setFormula(String formula);

    short getNpp();

    void setNpp(short p);
}
