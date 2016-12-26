package beans.sys;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import service.AbstractEJB;
import entities.sys.Menu;

/**
 *
 * @author savin
 */
@Stateless
public class MenuEJB extends AbstractEJB<Menu> {

    @Inject
    private EntityManager em;

    public MenuEJB() {
        super(Menu.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Menu> findAll() {
        List<Menu> l = super.findAll();
//        Collections.sort(l);
        Collections.sort(l, new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getMenuName().
                        compareTo(o2.getMenuName());
            }
        });
        return l;
    }

    public Short getLastLineNum(Long p_menu_id) {
        TypedQuery<Short> tq = em.createNamedQuery(Menu.LAST_LINE_NUM, Short.class).setParameter("p_menu_id", p_menu_id);
        return tq.getSingleResult();
    }

}
