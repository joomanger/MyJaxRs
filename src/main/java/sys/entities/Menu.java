package sys.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"menuName"}))
public class Menu implements Serializable, Comparable<Menu> {

    @Id
    @SequenceGenerator(name = "menu_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "menu_sq")
    private Long menu_id;
    private String menuName;
    private Boolean activeStatus = true;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="menu_id")
    private List<MenuItem> menuItems;

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

    @Override
    public int compareTo(Menu o) {
        if (menuName.charAt(0) > o.menuName.charAt(0)) {
            return 1;
        } else {
            return -1;
        }
    }

}
