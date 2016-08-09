package customer.beans;

import customer.entities.Country;
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
public class CountryEJB extends AbstractEJB<Country> {

    @Inject
    private EntityManager em;
    @Inject
    private SessionActions sa;

    public CountryEJB() {
        super(Country.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Country> findByLang() {
        TypedQuery<Country> tq = em.createNamedQuery(Country.FIND_BY_LANG, Country.class).setParameter("p_lang", sa.getLanguage());
        return tq.getResultList();
    }

    

    @Override
    public String create(Country entity) {
        return super.create(entity);
//        if (sa.getLanguage().equals("RU")) {
//            entity.setLanguage("US");
//            super.create(entity);
//        }
//        return res;
    }

}
