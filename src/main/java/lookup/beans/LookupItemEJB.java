package lookup.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lookup.entities.LookupItem;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class LookupItemEJB extends AbstractEJB<LookupItem> {

    @Inject
    private EntityManager em;

    public LookupItemEJB() {
        super(LookupItem.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Long getNextLookupItemID() {
        return (Long) em.createNativeQuery("select nextval('lookupitem_sq')").getSingleResult();
    }

    public List<String> getCitiesByRegion(String p_region) {
        List<String> l = em.createNativeQuery("select city_name from region_city where state_name=?1 order by city_name").setParameter(1, p_region).getResultList();
        return l;
    }

}
