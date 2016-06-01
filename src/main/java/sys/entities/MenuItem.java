package sys.entities;

import java.io.Serializable;
import javax.persistence.Entity;
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
public class MenuItem implements Serializable {

    public static final String FIND_BY_MENU_ID = "MenuItem.findByMenuId";
    @Id
    @SequenceGenerator(name = "menuItem_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "menuItem_sq")
    private Long menuItem_id;
    private String menuItem;
    private Short line_num;
    private Long view_id;
    @ManyToOne
    @JoinColumn(name="menu_id", nullable=false)
    private Menu menu;
    //private Long menu_id;

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

//    public View getView() {
//        return view;
//    }
//
//    public void setView(View view) {
//        this.view = view;
//    }
    public Long getView_id() {
        return view_id;
    }

    public void setView_id(Long view_id) {
        this.view_id = view_id;
    }

//    public Long getMenu_id() {
//        return menu_id;
//    }
//
//    public void setMenu_id(Long menu_id) {
//        this.menu_id = menu_id;
//    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    

}
