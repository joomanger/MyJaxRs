package sys.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.Secure;
import sys.entities.SysRole;
import sys.entities.SysUser;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class OpenUserView implements Serializable {

    @Inject
    private FindUserSession fus;
    @Inject
    private UserCBean client;

    private List<SysRole> selectedRoles = new ArrayList<>();

    private SysUser user;

    private SysRole newRole;

    private String newPassword;
    private String reNewPassword;

    private Boolean passwordsEqual = false;
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
        user = client.findUserById(fus.getUser_id());
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
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

    public SysRole getNewRole() {
        return newRole;
    }

    public void setNewRole(SysRole newRole) {
        this.newRole = newRole;
    }

    public void checkPassword() {
        if (newPassword.equals(reNewPassword)) {
            this.passwordsEqual = true;
        } else {
            this.passwordsEqual = false;
        }
    }

    public void setPasswordsEqual(Boolean passwordsEqual) {
        this.passwordsEqual = passwordsEqual;
    }

    public Boolean isPasswordsEqual() {
        return passwordsEqual;
    }

}
