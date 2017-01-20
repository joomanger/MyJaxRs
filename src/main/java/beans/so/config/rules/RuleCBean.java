package beans.so.config.rules;

import entities.so.OrderLine;
import entities.so.config.rules.Rule;
import entities.so.config.rules.RuleLine;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import service.AbstractClientBean2;
import service.AbstractEJB2;
import service.IAttributes;
import service.LogUtils;
import service.SessionActions;
import service.WhoIS;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class RuleCBean extends AbstractClientBean2<Rule> {

    private static final Logger LOG = Logger.getLogger(LogUtils.getClassName());

    @Inject
    private RuleEJB ejb;

    @Inject
    private SessionActions sa;

    @Override
    protected AbstractEJB2<Rule> getEJB() {
        return ejb;
    }

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
        MDC.put("user_name", FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
    }

    public void addFormulaLine(IRuleView view) {
        LOG.debug("Создание формулы");
        IAttributes attr = new OrderLine();
        RuleLine rl = new RuleLine();
        String[] elems = RuleUtils.getFormulaElemets(view.getFormula(), attr);
        rl.setAttributeResult(elems[0]);
        rl.setAttributeFormula(elems[1]);
        rl.setFormula(view.getFormula());
        short s = view.getNpp();
        view.setNpp(++s);
        rl.setLine_number(view.getNpp());
        if (rl instanceof WhoIS) {
            sa.createWHO(rl);
        }
        ((Rule) view.getEntity()).addLine(rl);
        view.setFormula(null);
        view.setIsItemDisable(true);

    }

    public void editFormulaLine(IRuleView view) {
        LOG.debug("Обновление формулы");
        IAttributes attr = new OrderLine();
        RuleLine rl = view.getEditedRuleLine();
        String[] elems = RuleUtils.getFormulaElemets(view.getFormula(), attr);
        rl.setAttributeResult(elems[0]);
        rl.setAttributeFormula(elems[1]);
        rl.setFormula(view.getFormula());
        if (rl instanceof WhoIS) {
            sa.updateWHO(rl);
        }
        view.setFormula(null);
    }

    public void deleteSelectedFormula(IRuleView view) {
        ((Rule) view.getEntity()).getLines().removeAll(view.getSelectedEntityLines());
    }

}
