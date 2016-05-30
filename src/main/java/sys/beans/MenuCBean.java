package sys.beans;

import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sys.entities.Menu;
import sys.entities.MenuItem;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class MenuCBean {
    
    @Inject
    private MenuEJB ejb;
    @Inject
    private MenuItemEJB itemEJB;
    @Inject
    private FindMenuView fmv;
    @Inject
    private NewMenuView nmv;
    
    public List<Menu> findAll() {
        List<Menu> menus = ejb.findAll();
        Collections.sort(menus);
        return menus;
    }
    
    public void deleteMenus() {
        for (Menu menu : fmv.getSelectedMenus()) {
            String status = ejb.remove(menu);
            ejb.sendMessage(status, "Меню " + menu.getMenuName() + " удалено успешно");
        }
    }
    
    public void createMenu() {
        Menu menu = nmv.getMenu();
        String status = ejb.create(menu);
        ejb.sendMessage(status, "Меню создано успешно");
        nmv.setB1(true);
    }
    
    public void deleteMenuItems() {
        for (MenuItem mi : nmv.getSelectedMenuItems()) {
            String status = itemEJB.remove(mi);
            itemEJB.sendMessage(status, "Пункт меню " + mi.getMenuItem() + " удален успешно");
        }
    }
    
}
