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
    @Inject
    private OpenMenuView omv;

    public List<Menu> findAll() {
        List<Menu> menus = ejb.findAll();
        Collections.sort(menus);
        return menus;
    }

    public Menu findMenu(Long p_menu_id) {
        return ejb.find(p_menu_id);
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

    public String createMenu() {
        Menu menu = nmv.getMenu();
        String status = ejb.create(menu);
        ejb.sendMessage(status, "Меню создано успешно");
        return "menus";
    }

    public void createMenu(Menu menu) {
        String status = ejb.create(menu);
        System.out.println("status=" + status);
        ejb.sendMessage(status, "Меню создано успешно");
        nmv.setB1(true);
    }

    public void addMenuItemNMV() {

        Menu m = nmv.getMenu();
        MenuItem mi = new MenuItem();
        nmv.next_line();
        mi.setView_id(nmv.getNewView().getView_id());
        mi.setLine_num(nmv.getLine_num());
        mi.setMenuItem(nmv.getNewMenuName());
//        mi.setMenu(m);
//        m.getMenuItems().add(mi);
        m.addMenuItem(mi);
        nmv.setNewView(null);
        nmv.setNewMenuName(null);
    }

    public void saveMenu(String msg) {
        String status = ejb.edit(omv.getMenu());
        ejb.sendMessage(status, msg);
    }

    public void saveMenuItem(MenuItem mi, String msg) {
        String status = itemEJB.edit(mi);
        itemEJB.sendMessage(status, msg);
    }

    public void addMenuItemOMV() {
        if ((omv.getNewMenuName().isEmpty()) || (omv.getNewView() == null)) {
            ejb.sendMessage("Пункт меню не должен быть пустым", null);
        } else {
            Menu m = omv.getMenu();
            MenuItem mi = new MenuItem();
//            omv.next_line();
            Short line_num = ejb.getLastLineNum(m.getMenu_id());
            if (line_num == null) {
                line_num = 1;
            } else {
                line_num++;
            }

            mi.setView_id(omv.getNewView().getView_id());
            mi.setLine_num(line_num);
            mi.setMenuItem(omv.getNewMenuName());
            m.addMenuItem(mi);
            saveMenu("Пункт добавлен успешно");
            //saveMenuItem(mi, "Пункт добавлен успешно");
            omv.setNewView(null);
            omv.setNewMenuName(null);
        }
    }

    public void deleteMenuItemsNMV() {
        nmv.getMenu().getMenuItems().removeAll(nmv.getSelectedMenuItems());
    }

    public void deleteMenuItemsOMV() {
        for (MenuItem mi : omv.getSelectedMenuItems()) {
            omv.getMenu().removeMenuItem(mi);
        }
        saveMenu("Пункты удалены успешно");
    }

    //For test
    public void addMenuItemNMV(Menu menu, MenuItem menuItem) {
        Menu m = menu;
        m.getMenuItems().add(menuItem);
        String status = null;
        try {
            status = ejb.edit(m);
            //m.getMenuItems().add(mi);
        } catch (Exception ex) {
            ejb.sendMessage("Ошибка вставки", null);
            nmv.prev_line();
        }

        itemEJB.sendMessage(status, "Пункт меню " + menuItem.getMenuItem() + " добавлен успешно");
        nmv.setNewMenuName(null);
        nmv.setNewView(null);
    }

    public List<MenuItem> findMenuItemsNMV() {
        return itemEJB.findByMenuId(nmv.getMenu().getMenu_id());
    }

    public Short getLastLineNum(Long p_menu_id) {
        Short rez = ejb.getLastLineNum(p_menu_id);
        if (rez == null) {
            return 0;
        } else {
            return rez;
        }
    }

}
