package beans.so.config.rules;

import entities.so.OrderLine;
import entities.so.config.rules.Rule;
import entities.so.config.rules.RuleLine;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractClientBean2;
import service.AbstractEJB2;
import service.SessionActions;
import service.WhoIS;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class RuleCBean extends AbstractClientBean2<Rule> {

    @Inject
    private RuleEJB ejb;
    
    @Inject
    private SessionActions sa;

    @Override
    protected AbstractEJB2<Rule> getEJB() {
        return ejb;
    }

    public void addFormulaLine(IRuleView view) {

        try {
            if (RuleUtils.checking(view.getFormula(), new OrderLine())) {
                RuleLine rl = new RuleLine();
                if (view.getFormula().isEmpty()) {
                    throw new RuntimeException("Формула не указана. Введите формулу!");
                }
                rl.setFormula(view.getFormula());
                short s = view.getNpp();
                view.setNpp(++s);
                rl.setLine_number(view.getNpp());
                if (rl instanceof WhoIS) {
                    sa.createWHO(rl);
                }
                ((Rule) view.getEntity()).addLine(rl);
                view.setFormula(null);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(RuleCBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
