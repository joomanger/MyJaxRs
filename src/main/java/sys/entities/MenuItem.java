package sys.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
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
        @NamedQuery(name = MenuItem.FIND_BY_MENU_ID, query = "select t from MenuItem t where t.menu_id=:p_menu_id"))
public class MenuItem implements Serializable {

    public static final String FIND_BY_MENU_ID = "MenuItem.findByMenuId";
    @Id
    @SequenceGenerator(name = "menuItem_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "menuItem_sq")
    private Long menuItem_id;
    private String menuItem;
//    private String url;
    private Short line_num;
    //@OneToMany
//    @JoinTable(name = "menuitems_views",
//            joinColumns = @JoinColumn(name = "menuitem_fk"),
//            inverseJoinColumns = @JoinColumn(name = "view_fk"))
//    private List<View> views;
    @ManyToOne
    @JoinColumn(name = "view_id")
    private View view;
    private Long menu_id;

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

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

}
