package beans.sys;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entities.sys.Menu;
import entities.sys.MenuItem;

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
        if (status.equals(ejb.SUCCESSFUL)) {
            return "menus";
        } else {
            return null;
        }
    }

    public void saveChangedLinesOMV() {
        for (Integer i : omv.getLinesForSave()) {
            MenuItem mi = omv.getMenu().getMenuItems().get(i);
            String status = itemEJB.edit(mi);
            itemEJB.sendMessage(status, "Позиция " + mi.getLine_num() + " изменена успешно");
        }
        omv.getLinesForSave().clear();
    }

    public void createMenu(Menu menu) {
        String status = ejb.create(menu);
        System.out.println("status=" + status);
        ejb.sendMessage(status, "Меню создано успешно");
        nmv.setB1(true);
    }

    public void saveMenu(String msg) {

        String status = ejb.edit(omv.getMenu());
        ejb.sendMessage(status, msg);
    }

    public void addMenuItemNMV() {
        Menu m = nmv.getMenu();
        MenuItem mi = new MenuItem();
        nmv.next_line();

        Long view_id;
        try {
            view_id = nmv.getNewView().getView_id();
        } catch (NullPointerException ex) {
            view_id = null;
        }

        String menuName;
        try {
            menuName = nmv.getNewMenuName();
        } catch (NullPointerException ex) {
            menuName = null;
        }
        mi.setView_id(view_id);
        mi.setLine_num(nmv.getLine_num());
        mi.setMenuItem(menuName);

        String result = itemEJB.validateMyEntity(mi);
        if (result.equals(itemEJB.SUCCESSFUL)) {
            m.addMenuItem(mi);
            nmv.setNewView(null);
            nmv.setNewMenuName(null);
        } else {
            nmv.prev_line();
            itemEJB.sendMessage(result, null);
        }
    }

    public void addMenuItemOMV() {
        Menu menu = omv.getMenu();
        MenuItem mi = new MenuItem();
        Short line_num = ejb.getLastLineNum(menu.getMenu_id());
        if (line_num == null) {
            line_num = 1;
        } else {
            line_num++;
        }

        Long view_id;
        try {
            view_id = omv.getNewView().getView_id();
        } catch (NullPointerException ex) {
            view_id = null;
        }

        String menuName;
        try {
            menuName = omv.getNewMenuName();
        } catch (NullPointerException ex) {
            menuName = null;
        }

        mi.setView_id(view_id);
        mi.setLine_num(line_num);
        mi.setMenuItem(menuName);
        mi.setMenu(menu);
        String status = itemEJB.create(mi);
        if (!status.equals(itemEJB.SUCCESSFUL)) {
            menu.getMenuItems().remove(mi);
        }
        itemEJB.sendMessage(status, "Пункт " + mi.getMenuItem() + " добавлен успешно");
        omv.setNewView(null);
        omv.setNewMenuName(null);
    }

    public void deleteMenuItemsNMV() {
        nmv.getMenu().getMenuItems().removeAll(nmv.getSelectedMenuItems());
    }

    public void deleteMenuItemsOMV() {
        boolean succ = true;
        for (MenuItem mi : omv.getSelectedMenuItems()) {
            String status = itemEJB.remove(mi);
            if (!status.equals(itemEJB.SUCCESSFUL)) {
                succ = false;
            } else {
                omv.getMenu().getMenuItems().remove(mi);
            }
        }
        if (succ) {
            itemEJB.sendMessage(itemEJB.SUCCESSFUL, "Пункты удалены успешно");
        } else {
            itemEJB.sendMessage(itemEJB.ERROR, "Ошибка при удалении");
        }
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
