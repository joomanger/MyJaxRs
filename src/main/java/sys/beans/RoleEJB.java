package sys.beans;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB;
import service.Secure;
import sys.entities.SysRole;

/**
 *
 * @author savin
 */
@Stateless
public class RoleEJB extends AbstractEJB<SysRole>{
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
        Collections.sort(l);
        return l;
    }
}
