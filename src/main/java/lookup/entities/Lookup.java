package lookup.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author savin
 */
@Entity
public class Lookup implements Serializable {

    @Id
    @SequenceGenerator(name = "lookup_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "lookup_sq")
    private Long id;
    private String name;
    private String description;
    private Boolean activeStatus=true;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "lookup")
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

    public void removeLookupItem(LookupItem li) {
        getLookupItems().remove(li);
        li.setLookup(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
