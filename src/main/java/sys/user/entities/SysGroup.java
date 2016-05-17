package sys.user.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author savin
 */
@Entity
@Table(name = "groups",uniqueConstraints
        = @UniqueConstraint(columnNames = {"groupname"}))
public class SysGroup implements Serializable {

    @Id
    @SequenceGenerator(name = "group_sq", initialValue = 2, allocationSize = 1)
    @GeneratedValue(generator = "group_sq")
    private Long group_id;
    private String groupname;
    private String description;

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
