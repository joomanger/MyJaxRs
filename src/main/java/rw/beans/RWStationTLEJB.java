/*

 */
package rw.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import rw.entities.RWStationTL;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class RWStationTLEJB extends AbstractEJB<RWStationTL> {

    @Inject
    private EntityManager em;

    public RWStationTLEJB() {
        super(RWStationTL.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
