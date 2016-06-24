package lookup.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author savin
 */
@Entity
public class LookupItem implements Serializable {

    @Id
    @SequenceGenerator(name = "lookupitem_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "lookupitem_sq")
    private Long id;
    private String valuez;
    private String valuezDescription;
    private Boolean activeStatus=true;
    
    @ManyToOne
    @JoinColumn(name = "LOOKUP_ID")
    private Lookup lookup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValuez() {
        return valuez;
    }

    public void setValuez(String valuez) {
        this.valuez = valuez;
    }

    public String getValuezDescription() {
        return valuezDescription;
    }

    public void setValuezDescription(String valuezDescription) {
        this.valuezDescription = valuezDescription;
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

}
