package sys.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sys.entities.SysUser;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class FindUserView implements Serializable{

    @Inject
    private UserEJB ejb;

    private List<SysUser> users = new ArrayList<>();
    private List<SysUser> selectedUsers = new ArrayList<>();

    @PostConstruct
    private void init() {
        users = ejb.findAll();
    }

    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }

    public List<SysUser> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<SysUser> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }
    
}
