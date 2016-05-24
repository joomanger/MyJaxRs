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

    public SysUser findUserById(Long p_id) {
        return ejb.find(p_id);
    }

    public SysUser findUserByUserName(String p_username) {
        return ejb.findByUserName(p_username);
    }

    public void deleteUsers() {
        
    }

    public String saveUser(SysUser user) {
        String result = ejb.edit(user);
        ejb.sendMessage(result, "Пользователь " + user.getUsername() + " сохранен");
        return "users";
    }

}
