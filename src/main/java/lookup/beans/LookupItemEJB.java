package lookup.beans;

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
   
}
