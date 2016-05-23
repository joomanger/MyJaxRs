package sys.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sys.entities.SysUser;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class UserCBean {

    @Inject
    private UserEJB ejb;

    public SysUser getUserById(Long p_id) {
        return ejb.find(p_id);
    }

    public SysUser getUserByUserName(String p_username) {
        return ejb.getByUserName(p_username);
    }

}
