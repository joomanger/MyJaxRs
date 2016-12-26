package beans.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import service.Secure;
import entities.sys.Menu;
import entities.sys.MenuItem;
import entities.sys.View;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class NewMenuView implements Serializable {

    private Menu menu=new Menu();
    private Boolean b1;
    private View newView;
    private List<MenuItem> selectedMenuItems = new ArrayList<>();
    private Short line_num = 0;
    private String newMenuName;

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
      //  menu = new Menu();
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

    public void next_line() {
        line_num++;
    }
    public void prev_line() {
        line_num--;
    }

    public Short getLine_num() {
        return line_num;
    }

    public String getNewMenuName() {
        return newMenuName;
    }

    public void setNewMenuName(String newMenuName) {
        this.newMenuName = newMenuName;
    }
    
}
