package customer.beans;

import customer.entities.CountryNew;
import customer.entities.CountryTL;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import service.AbstractEJB;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Stateless
public class CountryEJB2 extends AbstractEJB<CountryNew> {

    @Inject
    private EntityManager em;
    @Inject
    private SessionActions sa;

    public CountryEJB2() {
        super(CountryNew.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//    public List<CountryNew> findByLang() {
//        TypedQuery<CountryNew> tq = em.createNamedQuery(CountryNew.FIND_ALL, CountryNew.class);
//        return tq.getResultList();
//    }

    public List<CountryTL> findByLang() {
        return em.createNamedQuery(CountryTL.FIND_BY_LANG, CountryTL.class).setParameter("p_lang", sa.getLanguage()).getResultList();
    }

}
