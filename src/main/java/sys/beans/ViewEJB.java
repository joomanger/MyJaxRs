package sys.beans;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;
import service.Secure;
import service.SessionConfig;
import sys.entities.View;

/**
 *
 * @author savin
 */
@Stateless
public class ViewEJB extends AbstractEJB<View> {

    @Inject
    private EntityManager em;

    public ViewEJB() {
        super(View.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<View> findAll() {
        List<View> l = super.findAll();
        Collections.sort(l);
        return l;
    }

    public List<View> findViewsByUserName(String username) {
        return em.createNativeQuery("select v.view_id, v.viewName, v.description, v.url from users_views_v v where v.username='" + username + "'", "users_views_v").getResultList();
    }

}
