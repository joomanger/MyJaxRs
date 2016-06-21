package sys.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.Secure;
import sys.entities.SysUser;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class FindUserView implements Serializable {

    @Inject
    private UserEJB ejb;

    private List<SysUser> selectedUsers = new ArrayList<>();

    public List<SysUser> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<SysUser> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

}
