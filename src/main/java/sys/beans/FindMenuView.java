package sys.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.Secure;
import sys.entities.Menu;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class FindMenuView implements Serializable {

    @Inject
    private MenuCBean client;

    private List<Menu> selectedMenus = new ArrayList<>();
    private List<Menu> menus = new ArrayList<>();
    private List<Menu> filteredMenus = new ArrayList<>();

    @PostConstruct
    private void init() {
        menus = client.findAll();
    }

    public List<Menu> getSelectedMenus() {
        return selectedMenus;
    }

    public void setSelectedMenus(List<Menu> selectedMenus) {
        this.selectedMenus = selectedMenus;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Menu> getFilteredMenus() {
        return filteredMenus;
    }

    public void setFilteredMenus(List<Menu> filteredMenus) {
        this.filteredMenus = filteredMenus;
    }
    

}
