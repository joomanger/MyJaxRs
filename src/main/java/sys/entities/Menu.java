package sys.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"menuName"}))
@NamedQueries(
        @NamedQuery(name = Menu.LAST_LINE_NUM, query = "select max(t.line_num) from MenuItem t where t.menu.menu_id=:p_menu_id"))
public class Menu implements Serializable{

    public static final String LAST_LINE_NUM = "Menu.LAST_LINE_NUM";
    @Id
    @SequenceGenerator(name = "menu_sq", initialValue = 3, allocationSize = 1)
    @GeneratedValue(generator = "menu_sq")
    private Long menu_id;
    @Size(min=3,max=30,message = "Длина поля от 3 до 30 символов!")
    private String menuName;
    private Boolean activeStatus = true;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "menu")
    @PrivateOwned
    @OrderBy("line_num asc")
    private List<MenuItem> menuItems = new ArrayList<>();

    public Menu() {

    }

    public void addMenuItem(MenuItem mi) {
        addMenuItem(mi, true);
    }

    public void addMenuItem(MenuItem mi, boolean add) {
        if (mi != null) {
            getMenuItems().add(mi);
            if (add) {
                mi.setMenu(this, false);
            }
        }
    }

    public void removeMenuItem(MenuItem mi) {
        getMenuItems().remove(mi);
        mi.setMenu(null);

    }

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
