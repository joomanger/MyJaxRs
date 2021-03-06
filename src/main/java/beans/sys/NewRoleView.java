package beans.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
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
public class NewRoleView implements Serializable{
    private SysRole role=new SysRole();
    private List<Menu> selectedMenus=new ArrayList<>();
    private Menu newMenu;
    private Boolean b1=false;

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

    public Boolean getB1() {
        return b1;
    }

    public void setB1(Boolean b1) {
        this.b1 = b1;
    }
    
}
