package lookup.entities;

/**
 *
 * @author savin
 */
public class LookupItemVL extends LookupItem {

    private String meaning;
    private String description;

    public LookupItemVL() {
    }

    public LookupItemVL(Long lookupitem_id, Boolean activestatus, String valuez, String meaning, String description) {
        this.lookupItem_id = lookupitem_id;
        this.activeStatus = activestatus;
        this.valuez = valuez;
        this.meaning = meaning;
        this.description = description;
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
    
    public LookupItem getLookupItem(){
        return new LookupItem(lookupItem_id, valuez, lookup, activeStatus, lookupItemTL);
    }

}
