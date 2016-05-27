package sys.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sys.entities.Menu;
import sys.entities.SysRole;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewRoleView implements Serializable{
    private SysRole role=new SysRole();
    private List<Menu> selectedMenus=new ArrayList<>();
    private Menu newMenu;

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
