package entities.sys;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author savin
 */
@Entity
@Table(name = "views", uniqueConstraints
        = @UniqueConstraint(columnNames = {"viewName"}))
@SqlResultSetMapping(name = "users_views_v", entities = {
    @EntityResult(entityClass = View.class)})
public class View implements Serializable {

    @Id
    @SequenceGenerator(name = "view_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "view_sq")
    private Long view_id;
    private String viewName;
    private String description;
    private String url;

    public View() {
    }

    public View(Long view_id, String viewName, String description, String url) {
        this.view_id = view_id;
        this.viewName = viewName;
        this.description = description;
        this.url = url;
    }

    public Long getView_id() {
        return view_id;
    }

    public void setView_id(Long view_id) {
        this.view_id = view_id;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
