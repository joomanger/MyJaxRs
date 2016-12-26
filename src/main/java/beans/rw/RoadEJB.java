package beans.rw;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import entities.rw.RWRoad;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class RoadEJB extends AbstractEJB<RWRoad> {

    @Inject
    private EntityManager em;

    public RoadEJB() {
        super(RWRoad.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
