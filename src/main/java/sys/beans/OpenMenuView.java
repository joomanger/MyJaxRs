package sys.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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
public class OpenMenuView implements Serializable {

    @Inject
    private FindMenuSession fms;
    @Inject
    private MenuCBean client;

    private Menu menu;
    private List<MenuItem> selectedMenuItems = new ArrayList<>();
    private View newView;
    private String newMenuName;
    private Short line_num=0;

    @PostConstruct
    private void init() {
        menu = client.findMenu(fms.getMenu_id());
        System.out.println(menu.getMenuName()+" "+menu.getMenuItems().size());
        Collections.sort(menu.getMenuItems());
        line_num=client.getLastLineNum(fms.getMenu_id());
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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

    public String getNewMenuName() {
        return newMenuName;
    }

    public void setNewMenuName(String newMenuName) {
        this.newMenuName = newMenuName;
    }

    public Short getLine_num() {
        return line_num;
    }

    public void setLine_num(Short line_num) {
        this.line_num = line_num;
    }
    
    public void next_line(){
        line_num++;
    }

}
