package sys.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import service.AbstractEJB;
import sys.entities.SysUser;

/**
 *
 * @author savin
 */
@Stateless
public class UserEJB extends AbstractEJB<SysUser> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public UserEJB() {
        super(SysUser.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*---Дополнительные методы---*/
    public SysUser getByUserName(String p_username) {
        TypedQuery<SysUser> tq = em.createNamedQuery(SysUser.FIND_BY_USERNAME, SysUser.class).setParameter("p_username", p_username);
        return tq.getSingleResult();
    }
}
