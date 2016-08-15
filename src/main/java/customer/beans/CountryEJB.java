package customer.beans;

import customer.entities.Country;
import customer.entities.CountryVL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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

    private final List<CountryVL> countryVL = new ArrayList<>();

    public CountryEJB() {
        super(Country.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<CountryVL> findAllVL() {
        List<Object[]> a = em.createNativeQuery("select c.country_id, "
                + "t.name, t.description, c.eu_code,c.iso_code from Country c,CountryTL t "
                + "where c.country_id=t.country_id and t.language=?1 order by t.name").setParameter(1, sa.getLanguage()).getResultList();
        countryVL.clear();
        a.stream().forEach((aa) -> {
            for (int i = 0; i < 5; i++) {
                if (aa[i] == null) {
                    aa[i] = "";
                }
            }
            CountryVL c = new CountryVL(aa[0].toString(), aa[1].toString(), aa[2].toString(), aa[3].toString(), aa[4].toString());
            countryVL.add(c);
        });
        return countryVL;
    }

}
