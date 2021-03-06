package beans.customer;

import entities.customer.Country;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class CountryEJB extends AbstractEJB<Country> {

    @Inject
    private EntityManager em;

    public CountryEJB() {
        super(Country.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    // Мэппинг прописал в orm.xml, так как аннотации не воспринимает!!!
//    public List<CountryVL> findAllVL() {
//        List<CountryVL> a = em.createNativeQuery("select c.country_id, "
//                + "t.name, t.description, c.eu_code,c.iso_code from Country c,CountryTL t "
//                + "where c.country_id=t.country_id and t.language=?1 order by t.name", "CountryVLMapping").setParameter(1, sa.getLanguage()).getResultList();
//        return a;
//    }
//
//    public CountryVL findVL(String country_id) {
//        CountryVL a = (CountryVL) em.createNativeQuery("select c.country_id, "
//                + "t.name, t.description, c.eu_code,c.iso_code from Country c,CountryTL t "
//                + "where c.country_id=?1 and c.country_id=t.country_id and t.language=?2", "CountryVLMapping").setParameter(1, country_id).setParameter(2, sa.getLanguage()).getSingleResult();
//        return a;
//    }

}
