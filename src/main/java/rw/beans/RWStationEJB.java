package rw.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import rw.entities.RWStation;
import rw.entities.RWStationVL;
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
    // Мэппинг прописал в orm.xml, так как аннотации не воспринимает!!!
    public List<RWStationVL> findAllVL() {
        List<RWStationVL> a = em.createNativeQuery("select c.rws_code, t.name, r.rwr_code, r.fullname, r.shortname,r.country_id from RWStation c,RWStationTL t, RWRoad r " 
                        +"where c.rws_code=t.rws_code and r.rwr_code=c.rwroad_code and t.language=?1 order by t.name", "RWStationVLMapping").setParameter(1, sa.getLanguage()).getResultList();
        return a;
    }

//    public RWStationVL findVL(String country_id) {
//        RWStationVL a = (RWStationVL) em.createNativeQuery("select c.country_id, "
//                + "t.name, t.description, c.eu_code,c.iso_code from Country c,CountryTL t "
//                + "where c.country_id=?1 and c.country_id=t.country_id and t.language=?2", "RWStationVLMapping").setParameter(1, country_id).setParameter(2, sa.getLanguage()).getSingleResult();
//        return a;
//    }

}
