package sys.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sys.entities.SysRole;
import sys.entities.SysUser;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenUserView implements Serializable {

    @Inject
    private FindUserSession fus;
    @Inject
    private UserCBean client;
    
    private List<SysRole> selectedRoles=new ArrayList<>();

    private SysUser user;

    @PostConstruct
    private void init() {
        user = client.findUserById(fus.getUser_id());
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public List<SysRole> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<SysRole> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

}
