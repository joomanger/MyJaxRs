package sys.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"menuItem"}))
@NamedQueries(
        @NamedQuery(name = MenuItem.FIND_BY_MENU_ID, query = "select t from MenuItem t where t.menu.menu_id=:p_menu_id order by t.line_num"))
public class MenuItem implements Serializable, Comparable<MenuItem> {

    public static final String FIND_BY_MENU_ID = "MenuItem.findByMenuId";
    @Id
    @SequenceGenerator(name = "menuItem_sq", initialValue = 10, allocationSize = 1)
    @GeneratedValue(generator = "menuItem_sq")
    private Long menuItem_id;
    private String menuItem;
    private Short line_num;
    private Long view_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MENU_ID", nullable = false, updatable = false, insertable = true)
    private Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        setMenu(menu, true);
    }

    public void setMenu(Menu menu, boolean set) {
        this.menu = menu;
        if (menu != null && set) {
            menu.addMenuItem(this, false);
        }
    }

    public Long getMenuItem_id() {
        return menuItem_id;
    }

    public void setMenuItem_id(Long menuItem_id) {
        this.menuItem_id = menuItem_id;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public Short getLine_num() {
        return line_num;
    }

    public void setLine_num(Short line_num) {
        this.line_num = line_num;
    }

    public Long getView_id() {
        return view_id;
    }

    public void setView_id(Long view_id) {
        this.view_id = view_id;
    }

    @Override
    public int compareTo(MenuItem o) {
        if (line_num > o.getLine_num()) {
            return 1;
        } else {
            return -1;
        }
    }

}
