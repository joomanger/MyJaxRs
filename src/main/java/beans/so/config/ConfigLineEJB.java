package beans.so.config;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB;
import entities.so.config.ConfigurationLine;

/**
 *
 * @author savin
 */
@Stateless
public class ConfigLineEJB extends AbstractEJB<ConfigurationLine> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public ConfigLineEJB() {
        super(ConfigurationLine.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*---Дополнительные методы---*/
}
