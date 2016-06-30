package lookup.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"lookup_id","valuez"}))
public class LookupItem implements Serializable {

    @Id
    @SequenceGenerator(name = "lookupitem_sq", initialValue = 38, allocationSize = 1)
    @GeneratedValue(generator = "lookupitem_sq")
    private Long lookupItem_id;
    @Size(min=1,max=30,message = "Длина поля ЗНАЧЕНИЕ от 1 до 30 символов!")
    private String valuez;
    private String valuezDescription;
    private Boolean activeStatus=true;
    
    @ManyToOne
    @JoinColumn(name = "LOOKUP_ID")
    private Lookup lookup;

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
