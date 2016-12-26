/*

 */
package beans.customer;

import entities.customer.CountryTL;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class CountryTLEJB extends AbstractEJB<CountryTL> {

    @Inject
    private EntityManager em;

    public CountryTLEJB() {
        super(CountryTL.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
