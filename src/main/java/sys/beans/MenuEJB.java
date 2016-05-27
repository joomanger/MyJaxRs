package sys.beans;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB;
import sys.entities.Menu;

/**
 *
 * @author savin
 */
@Stateless
public class MenuEJB extends AbstractEJB<Menu> {
    @PersistenceContext(unitName = "myjaxrs")
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
    
}
