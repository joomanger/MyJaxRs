package sys.beans;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import service.AbstractEJB;
import service.Secure;
import sys.entities.Menu;

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
        Collections.sort(l);
        return l;
    }

    public Short getLastLineNum(Long p_menu_id) {
        TypedQuery<Short> tq = em.createNamedQuery(Menu.LAST_LINE_NUM, Short.class).setParameter("p_menu_id", p_menu_id);
        return tq.getSingleResult();
    }

}
