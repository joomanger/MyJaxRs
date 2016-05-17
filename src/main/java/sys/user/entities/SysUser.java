package sys.user.entities;

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
@Table(name = "users", uniqueConstraints
        = @UniqueConstraint(columnNames = {"username"}))
public class SysUser implements Serializable {

    @Id
    @SequenceGenerator(name = "user_sq", initialValue = 2, allocationSize = 1)
    @GeneratedValue(generator = "user_sq")
    private Long user_id;
    private String username;
    private String fullName;
    private String password;
    @OneToMany
    @JoinTable(name = "users_groups",
            joinColumns = @JoinColumn(name = "user_fk"),
            inverseJoinColumns = @JoinColumn(name = "group_fk"))
    private List<SysGroup> groups;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<SysGroup> groups) {
        this.groups = groups;
    }

}
