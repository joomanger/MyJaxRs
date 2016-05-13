package so.config.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB;
import so.config.entity.ParameterConfigurationValues;

/**
 *
 * @author savin
 */
@Stateless
public class ParameterValuesEJB extends AbstractEJB<ParameterConfigurationValues> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public ParameterValuesEJB() {
        super(ParameterConfigurationValues.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
