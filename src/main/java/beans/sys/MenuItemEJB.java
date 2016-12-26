package beans.sys;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import service.AbstractEJB;
import entities.sys.MenuItem;

/**
 *
 * @author savin
 */
@Stateless
public class MenuItemEJB extends AbstractEJB<MenuItem> {

    //@PersistenceContext(unitName = "myjaxrs")
    @Inject
    private EntityManager em;

    public MenuItemEJB() {
        super(MenuItem.class);
    }

    @Override
    public EntityManager getEntityManager() {
        //em = Persistence.createEntityManagerFactory("test").createEntityManager();
        return em;
    }

    /*---Дополнительные методы---*/
    public List<MenuItem> findByMenuId(Long menu_id) {
        TypedQuery<MenuItem> tp = em.createNamedQuery(MenuItem.FIND_BY_MENU_ID, MenuItem.class).setParameter("p_menu_id", menu_id);
        return tp.getResultList();
    }
}
