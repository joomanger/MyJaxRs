package beans.sys;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB;
import entities.sys.SysRole;

/**
 *
 * @author savin
 */
@Stateless
public class RoleEJB extends AbstractEJB<SysRole> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public RoleEJB() {
        super(SysRole.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<SysRole> findAll() {
        List<SysRole> l = super.findAll();
//        Collections.sort(l);
        Collections.sort(l, new Comparator<SysRole>() {
            @Override
            public int compare(SysRole o1, SysRole o2) {
                return o1.getRoleName().
                        compareTo(o2.getRoleName());
            }
        });
        return l;
    }
}
