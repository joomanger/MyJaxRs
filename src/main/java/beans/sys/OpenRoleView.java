package beans.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.Secure;
import entities.sys.Menu;
import entities.sys.SysRole;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class OpenRoleView implements Serializable {

    @Inject
    private RoleCBean client;
    private SysRole role;
    private List<Menu> selectedMenus = new ArrayList<>();
    private Menu newMenu;
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
        role = client.findRole();
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public List<Menu> getSelectedMenus() {
        return selectedMenus;
    }

    public void setSelectedMenus(List<Menu> selectedMenus) {
        this.selectedMenus = selectedMenus;
    }

    public Menu getNewMenu() {
        return newMenu;
    }

    public void setNewMenu(Menu newMenu) {
        this.newMenu = newMenu;
    }

}
