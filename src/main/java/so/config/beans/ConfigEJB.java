package so.config.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import service.AbstractEJB;
import so.config.entity.Configuration;
import so.config.entity.ConfigurationLine;

/**
 *
 * @author savin
 */
@Stateless
public class ConfigEJB extends AbstractEJB<Configuration> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public ConfigEJB() {
        super(Configuration.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*---Дополнительные методы---*/
    public List<ConfigurationLine> getValues(Long id) {
        TypedQuery<ConfigurationLine> tq = em.createNamedQuery(ConfigurationLine.FIND_BY_HEADER_ID, ConfigurationLine.class).setParameter("p_header_id", id);
        return tq.getResultList();
    }

    public Configuration getConfig(Long p_item_id, Integer p_ver_num) {
        TypedQuery<Configuration> tq = em.createNamedQuery(Configuration.FIND_HEADER_ID_BY_LAST_VER_NUM_AND_ITEM_ID, Configuration.class)
                .setParameter("p_item_id", p_item_id)
                .setParameter("p_ver_num", p_ver_num);
        try {
            Configuration c = tq.getSingleResult();
            return c;
        } catch (javax.persistence.NoResultException ex) {
            return null;
        }
    }

    public Integer getLastVersion(Long id) {
        TypedQuery<Integer> tq = em.createNamedQuery(Configuration.FIND_LAST_VERSION_BY_ITEM, Integer.class).setParameter("p_item_id", id);
        return tq.getSingleResult();
    }

    public Integer getMaxLineNum(Long id) {
        TypedQuery<Integer> tq = em.createNamedQuery(ConfigurationLine.MAX_LINE_NUM_BY_HEADER_ID, Integer.class).setParameter("p_header_id", id);
        return tq.getSingleResult();
    }

}
