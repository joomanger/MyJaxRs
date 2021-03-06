package beans.sys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import service.AbstractEJB;
import entities.sys.SysGroup;
import entities.sys.SysUser;

/**
 *
 * @author savin
 */
@Stateless
public class UserEJB extends AbstractEJB<SysUser> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;
//    @Inject
//    private SessionConfig sc;

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
//        Collections.sort(l);
        Collections.sort(l, new Comparator<SysUser>() {
            @Override
            public int compare(SysUser o1, SysUser o2) {
                return o1.getUsername().
                        compareTo(o2.getUsername());
            }
        });
        return l;
    }

    /*---Дополнительные методы---*/
    public SysUser findByUserName(String p_username) {
        if (!p_username.isEmpty()) {
            try {
                TypedQuery<SysUser> tq = em.createNamedQuery(SysUser.FIND_BY_USERNAME, SysUser.class).setParameter("p_username", p_username);
                return tq.getSingleResult();
            } catch (Exception ex) {
                return null;
            }
        }
        return null;
    }

    /*регистрация пользователя(нового) в группе USER*/
    public SysUser registerNewUser(SysUser user) {
        try {
            TypedQuery<SysGroup> tq = em.createNamedQuery(SysGroup.FIND_BY_GROUP_NAME, SysGroup.class).setParameter("p_groupname", SysGroup.USERS_GROUP);
            List<SysGroup> groups = new ArrayList<>();
            groups.add(tq.getSingleResult());
            user.setGroups(groups);
            System.out.println("User [" + user.getUsername() + "] is registered into group [" + SysGroup.USERS_GROUP + "]");
            return user;
        } catch (Exception ex) {
            System.out.println("exception sys.beans.UserEJB.registerNewUser() " + ex.getMessage());
        }
        return null;
    }
}
