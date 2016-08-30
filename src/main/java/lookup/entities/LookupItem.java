package lookup.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"lookup_id", "valuez"}))
public class LookupItem implements Serializable {

    @Id
    @SequenceGenerator(name = "lookupitem_sq", initialValue = 38, allocationSize = 1)
    @GeneratedValue(generator = "lookupitem_sq")
    private Long lookupItem_id;
    @Size(min = 1, max = 50, message = "Длина поля ЗНАЧЕНИЕ от 1 до 50 символов!")
    private String valuez;
    //private String valuezDescription;
    private Boolean activeStatus = true;

    @ManyToOne
    @JoinColumn(name = "LOOKUP_ID")
    private Lookup lookup;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "lookupItem")
    @PrivateOwned
    @OrderBy("language asc")
    private List<LookupItemTL> lookupItemTL = new ArrayList<>();
    @Transient
    private String meaning;
    @Transient
    private String description;

    public LookupItem() {
    }

//    public LookupItem(Long lookupItem_id, String valuez, Lookup lookup, Boolean activeStatus, List<LookupItemTL> lookupItemTL) {
//        this.lookupItem_id = lookupItem_id;
//        this.valuez = valuez;
//        this.lookup = lookup;
//        this.activeStatus = activeStatus;
//        this.lookupItemTL = lookupItemTL;
//    }
    public LookupItem(Long lookupItem_id, Boolean activeStatus, String valuez, String meaning, String description) {
        this.lookupItem_id = lookupItem_id;
        this.activeStatus = activeStatus;
        this.valuez = valuez;
        this.meaning = meaning;
        this.description = description;
    }

    public List<LookupItemTL> getLookupItemTL() {
        return lookupItemTL;
    }

    public void setLookupItemTL(List<LookupItemTL> lookupItemTL) {
        this.lookupItemTL = lookupItemTL;
    }

    public void addLookupItemTL(LookupItemTL li) {
        addLookupItemTL(li, true);
    }

    public void addLookupItemTL(LookupItemTL li, boolean add) {
        if (li != null) {
            getLookupItemTL().add(li);
            if (add) {
                li.setLookupItem(this, false);
            }
        }
    }

    public Long getLookupItem_id() {
        return lookupItem_id;
    }

    public void setLookupItem_id(Long lookupItem_id) {
        this.lookupItem_id = lookupItem_id;
    }

    public String getValuez() {
        return valuez;
    }

    public void setValuez(String valuez) {
        this.valuez = valuez;
    }

    public Lookup getLookup() {
        return lookup;
    }

    public void setLookup(Lookup lookup) {
        setLookup(lookup, true);
    }

    public void setLookup(Lookup lookup, boolean set) {
        this.lookup = lookup;
        if (lookup != null && set) {
            lookup.addLookupItem(this, false);
        }
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LookupItem other = (LookupItem) obj;
        if (!Objects.equals(this.valuez, other.valuez)) {
            return false;
        }
        if (!Objects.equals(this.activeStatus, other.activeStatus)) {
            return false;
        }
        if (!Objects.equals(this.getLookupItem_id(), other.getLookupItem_id())) {
            return false;
        }
        return true;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
