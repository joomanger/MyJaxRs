package beans.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import service.Secure;
import entities.sys.SysRole;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class FindRoleView implements Serializable {

    private List<SysRole> selectedRoles = new ArrayList<>();

    public List<SysRole> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<SysRole> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

}
