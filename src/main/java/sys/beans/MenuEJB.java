package sys.beans;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;
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

}
