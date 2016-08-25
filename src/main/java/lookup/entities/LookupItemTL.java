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
import javax.validation.constraints.NotNull;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"language", "lookupitem_id"}))
public class LookupItemTL implements Serializable {

    @Id
    @SequenceGenerator(name = "lookupitemtl_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "lookupitemtl_sq")
    private Long lookupitemtl_id;
    @NotNull
    private String language;
    @NotNull
    private String meaning;
    private String description;
    @ManyToOne
    @JoinColumn(name = "LOOKUPITEM_ID")
    private LookupItem lookupItem;

    public Long getLookupitemtl_id() {
        return lookupitemtl_id;
    }

    public void setLookupitemtl_id(Long lookupitemtl_id) {
        this.lookupitemtl_id = lookupitemtl_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public LookupItem getLookupItem() {
        return lookupItem;
    }

    public void setLookupItem(LookupItem lookupItem) {
        setLookupItem(lookupItem, true);
    }

    public void setLookupItem(LookupItem lookupItem, boolean set) {
        this.lookupItem = lookupItem;
        if (lookupItem != null && set) {
            lookupItem.addLookupItemTL(this, false);
        }
    }

}
