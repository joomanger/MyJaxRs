package sys.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB;
import sys.entities.MenuItem;

/**
 *
 * @author savin
 */
@Stateless
public class MenuItemEJB extends AbstractEJB<MenuItem> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public MenuItemEJB() {
        super(MenuItem.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*---Дополнительные методы---*/
}
