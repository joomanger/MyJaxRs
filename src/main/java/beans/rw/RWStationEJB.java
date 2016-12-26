package beans.rw;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import entities.rw.RWStation;
import entities.rw.RWStationVL;
import service.AbstractEJB;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Stateless
public class RWStationEJB extends AbstractEJB<RWStation> {

    @Inject
    private EntityManager em;
    @Inject
    private SessionActions sa;

    public RWStationEJB() {
        super(RWStation.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//    @Override
//    public List<RWStation> findAll() {
//        return em.createNamedQuery(RWStation.FIND_ALL_STATIONS, RWStation.class).setParameter("p_lang", sa.getLanguage()).getResultList();
//
//    }

    public String getNameByRWcode(String p_rws_code) {
        return String.valueOf(em.createNativeQuery("select t.name from RWStationTL t"
                + " where t.rws_code=?1 and t.language=?2").setParameter(1, p_rws_code).setParameter(2, sa.getLanguage()).getSingleResult());
    }

    // Мэппинг прописал в orm.xml, так как аннотации не воспринимает!!!
    public List<RWStationVL> findAllVL() {
        return em.createNativeQuery("select t.rws_code, tl.name as rws_name, r.fullName as "
                + "rwr_name from RWStation t, RWStationTL tl, RWRoad r "
                + "where t.rws_code=tl.rws_code and tl.language=?1 and r.rwr_code=t.rwroad_code "
                + "order by rws_name", "RWStationVLMapping").
                setParameter(1, sa.getLanguage()).getResultList();
    }

}
