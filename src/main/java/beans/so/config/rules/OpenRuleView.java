package beans.so.config.rules;

import entities.so.config.rules.Rule;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenRuleView extends AbstractRuleView<Rule> {

    @Inject
    private RuleCBean client;
    
    @ManagedProperty("#{param.foo}")
    private Long foo;

    //private String formula;

    private Long rule_id;

    @PostConstruct
    @Override
    protected void init() {
        FacesContext.getCurrentInstance().
        System.out.println("init OpenRuleView foo=" + foo);
    }

    public void init2() {
        System.out.println("qwqwqw rule_id="+getRule_id() );
        super.setEntity(client.find(getRule_id()));
    }

//    public String getFormula() {
//        return formula;
//    }
//
//    public void setFormula(String formula) {
//        this.formula = formula;
//    }

    public Long getRule_id() {
        return rule_id;
    }

    public void setRule_id(Long rule_id) {
        System.out.println("rulik="+rule_id);
        this.rule_id = rule_id;
        super.setEntity(client.find(rule_id));
    }

    public Long getFoo() {
        return foo;
    }

    public void setFoo(Long foo) {
        this.foo = foo;
    }
    
    

}
