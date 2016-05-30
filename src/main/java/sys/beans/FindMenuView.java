package sys.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sys.entities.Menu;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class FindMenuView implements Serializable {
    
    private List<Menu> selectedMenus = new ArrayList<>();

    public List<Menu> getSelectedMenus() {
        return selectedMenus;
    }

    public void setSelectedMenus(List<Menu> selectedMenus) {
        this.selectedMenus = selectedMenus;
    }
    
    
    
}
