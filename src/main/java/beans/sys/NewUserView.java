package beans.sys;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import service.AbstractView;
import service.Secure;
import entities.sys.SysRole;
import entities.sys.SysUser;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class NewUserView extends AbstractView<SysUser>{

//    @Inject
//    private FindUserSession fus;
//    @Inject
//    private UserCBean client;

//    private List<SysRole> selectedRoles = new ArrayList<>();

    private SysUser entity=new SysUser();

    private SysRole newRole=new SysRole();

    private String newPassword;
    private String reNewPassword;

    private Boolean passwordsEqual = false;

    private Boolean fieldDisabled = false;
    
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(entity);
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

//    public SysUser getUser() {
//        return user;
//    }
//
//    public void setUser(SysUser user) {
//        this.user = user;
//    }
//
//    public List<SysRole> getSelectedRoles() {
//        return selectedRoles;
//    }
//
//    public void setSelectedRoles(List<SysRole> selectedRoles) {
//        this.selectedRoles = selectedRoles;
//    }

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

    public Boolean isFieldDisabled() {
        return fieldDisabled;
    }

    public void setFieldDisabled(Boolean fieldDisabled) {
        this.fieldDisabled = fieldDisabled;
    }

   

}
