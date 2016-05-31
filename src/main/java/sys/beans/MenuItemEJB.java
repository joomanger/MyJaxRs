package sys.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public List<MenuItem> findByMenuId(Long menu_id) {
        TypedQuery<MenuItem> tp = em.createNamedQuery(MenuItem.FIND_BY_MENU_ID, MenuItem.class).setParameter("p_menu_id", menu_id);
        return tp.getResultList();
    }
}
