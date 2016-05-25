package sys.beans;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import service.AbstractEJB;
import service.SessionConfig;
import sys.entities.SysUser;

/**
 *
 * @author savin
 */
@Stateless
public class UserEJB extends AbstractEJB<SysUser> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;
    @Inject
    private SessionConfig sc;

    public UserEJB() {
        super(SysUser.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<SysUser> findAll() {
        List<SysUser> l = super.findAll();
        Collections.sort(l);
        return l;
    }

    /*---Дополнительные методы---*/
    public SysUser findByUserName(String p_username) {
        TypedQuery<SysUser> tq = em.createNamedQuery(SysUser.FIND_BY_USERNAME, SysUser.class).setParameter("p_username", p_username);
        return tq.getSingleResult();
    }
}
