package entities.lookup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author savin
 */
@Entity
@NamedQueries(
        @NamedQuery(name = Lookup.FIND_BY_NAME, query = "select t from Lookup t where t.name=:p_name")
)
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"name"}))
public class Lookup implements Serializable {

    public static final String FIND_BY_NAME = "Lookup.FIND_BY_NAME";
    @Id
    @SequenceGenerator(name = "lookup_sq", initialValue = 4, allocationSize = 1)
    @GeneratedValue(generator = "lookup_sq")
    private Long lookup_id;
    @Size(min = 3, max = 30, message = "Длина поля ИМЯ от 3 до 30 символов!")
    private String name;
    private String description;
    private Boolean activeStatus = true;
    private Boolean systemLookup = false;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "lookup", fetch = FetchType.LAZY)
    @PrivateOwned
    @OrderBy("valuez asc")
    private List<LookupItem> lookupItems = new ArrayList<>();

    public void addLookupItem(LookupItem li) {
        addLookupItem(li, true);
    }

    public void addLookupItem(LookupItem li, boolean add) {
        if (li != null) {
            getLookupItems().add(li);
            if (add) {
                li.setLookup(this, false);
            }
        }
    }

    @Transient
    public LookupItemTL getTranslateObject(String p_value, String p_lang) {
        for (LookupItem i : getLookupItems()) {
            if (i.getValuez().equals(p_value)) {
                for (LookupItemTL tl : i.getLookupItemTL()) {
                    if (tl.getLanguage().equals(p_lang)) {
                        return tl;
                    }
                }
            }
        }
        return null;
    }

    public void removeLookupItem(LookupItem li) {
        getLookupItems().remove(li);
        li.setLookup(null);
    }

    public Long getLookup_id() {
        return lookup_id;
    }

    public void setLookup_id(Long lookup_id) {
        this.lookup_id = lookup_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public List<LookupItem> getLookupItems() {
        return lookupItems;
    }

    public void setLookupItems(List<LookupItem> lookupItems) {
        this.lookupItems = lookupItems;
    }

    public Boolean getSystemLookup() {
        return systemLookup;
    }

    public void setSystemLookup(Boolean systemLookup) {
        this.systemLookup = systemLookup;
    }

//    @Override
//    public int compareTo(Lookup o) {
//        if (this.name.charAt(0) > o.name.charAt(0)) {
//            return 1;
//        } else {
//            return -1;
//        }
//    }
}
