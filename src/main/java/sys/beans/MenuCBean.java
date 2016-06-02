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
            fmv.getMenus().remove(menu);
        }

    }

    public void deleteMenu(Menu menu) {
        String status = ejb.remove(menu);
        ejb.sendMessage(status, "Меню " + menu.getMenuName() + " удалено успешно");
        fmv.getMenus().remove(menu);
    }

    public void createMenu() {
        Menu menu = nmv.getMenu();
        String status = ejb.create(menu);
        ejb.sendMessage(status, "Меню создано успешно");
        nmv.setB1(true);
    }

    public void createMenu(Menu menu) {
        String status = ejb.create(menu);
        System.out.println("status=" + status);
        ejb.sendMessage(status, "Меню создано успешно");
        nmv.setB1(true);
    }

    public void deleteMenuItems() {
        for (MenuItem mi : nmv.getSelectedMenuItems()) {
            String status = itemEJB.remove(mi);
            itemEJB.sendMessage(status, "Пункт меню " + mi.getMenuItem() + " удален успешно");
            nmv.getMenu().getMenuItems().remove(mi);
        }
//        Menu m = nmv.getMenu();
//        m.getMenuItems().removeAll(nmv.getSelectedMenuItems());
//        String status = ejb.edit(m);
//        ejb.sendMessage(status, "Удаление выполнено успешно");
    }

    public void addMenuItemNMV() {
        Menu m = nmv.getMenu();
        nmv.next_line();
        MenuItem mi = new MenuItem();
        mi.setView_id(nmv.getNewView().getView_id());
        mi.setLine_num(nmv.getLine_num());
        mi.setMenuItem(nmv.getNewMenuName());
        m.getMenuItems().add(mi);
        String status = null;
        try {
            status = ejb.edit(m);
            //m.getMenuItems().add(mi);
        } catch (Exception ex) {
            ejb.sendMessage("Ошибка вставки", null);
            nmv.prev_line();
        }

        itemEJB.sendMessage(status, "Пункт меню " + mi.getMenuItem() + " добавлен успешно");
        nmv.setNewMenuName(null);
        nmv.setNewView(null);
    }

    public List<MenuItem> findMenuItemsNMV() {
        return itemEJB.findByMenuId(nmv.getMenu().getMenu_id());
    }

}
