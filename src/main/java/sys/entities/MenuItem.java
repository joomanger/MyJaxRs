package sys.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class MenuItem implements Serializable {

    @Id
    @SequenceGenerator(name = "menuItem_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "menuItem_sq")
    private Long menuItem_id;
    private String menuItem;
    private String url;
    private Short npp;
    @OneToMany
    @JoinTable(name = "menuitems_views",
            joinColumns = @JoinColumn(name = "menuitem_fk"),
            inverseJoinColumns = @JoinColumn(name = "view_fk"))
    private List<View> views;

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

    public Short getNpp() {
        return npp;
    }

    public void setNpp(Short npp) {
        this.npp = npp;
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
