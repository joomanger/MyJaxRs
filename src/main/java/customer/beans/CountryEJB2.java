package customer.beans;

import customer.entities.CountryNew;
import customer.entities.CountryTL;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;
import service.AbstractEJB;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Stateless
@SqlResultSetMapping(
        name = "CountryMapping",
        entities = {
            @EntityResult(entityClass = CountryNew.class),
            @EntityResult(entityClass = CountryTL.class)
        }
)
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

    public void findByLang2() {
        List<Object[]> a = em.createNativeQuery("select c.country_id,c.eu_code,c.iso_code, "
                + "t.countrytl_id, t.country_id, t.name, t.description from CountryNew c,CountryTL t "
                + "where c.country_id=t.country_id and t.language=?1", "CountryMapping").setParameter(1, sa.getLanguage()).getResultList();
        a.stream().forEach((aa) -> {
            //System.out.println(((CountryNew) aa[0]).getEu_code());
            System.out.println(aa.length);
        });
    }

    public List<CountryTL> findByLang() {
        findByLang2();
        return em.createNamedQuery(CountryTL.FIND_BY_LANG, CountryTL.class).setParameter("p_lang", sa.getLanguage()).getResultList();
    }

}
