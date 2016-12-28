package beans.so.config.rules;

import entities.so.config.rules.Rule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB2;

/**
 *
 * @author savin
 */
@Stateless
public class RuleEJB extends AbstractEJB2<Rule> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public RuleEJB() {
        super(Rule.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
