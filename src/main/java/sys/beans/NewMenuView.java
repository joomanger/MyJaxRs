package sys.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sys.entities.Menu;
import sys.entities.MenuItem;
import sys.entities.View;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewMenuView implements Serializable {
    
    private Menu menu;
    private Boolean b1;
    private View newView;
    private List<MenuItem> selectedMenuItems = new ArrayList<>();

    @PostConstruct
    private void init() {
        menu = new Menu();
        menu.setActiveStatus(true);
    }
    
    public Menu getMenu() {
        return menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    public Boolean getB1() {
        return b1;
    }
    
    public void setB1(Boolean b1) {
        this.b1 = b1;
    }
    
    public List<MenuItem> getSelectedMenuItems() {
        return selectedMenuItems;
    }
    
    public void setSelectedMenuItems(List<MenuItem> selectedMenuItems) {
        this.selectedMenuItems = selectedMenuItems;
    }

    public View getNewView() {
        return newView;
    }

    public void setNewView(View newView) {
        this.newView = newView;
    }
    
    
}
