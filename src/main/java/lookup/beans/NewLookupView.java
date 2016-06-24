package lookup.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lookup.entities.Lookup;
import lookup.entities.LookupItem;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewLookupView implements Serializable {

    private Lookup lookup=new Lookup();
    private List<LookupItem> selectedLookupItems = new ArrayList<>();
    private boolean b1 = false;
    private String newLookupName;
    private String newLookupDesc;

    public String getNewLookupName() {
        return newLookupName;
    }

    public void setNewLookupName(String newLookupName) {
        this.newLookupName = newLookupName;
    }

    public String getNewLookupDesc() {
        return newLookupDesc;
    }

    public void setNewLookupDesc(String newLookupDesc) {
        this.newLookupDesc = newLookupDesc;
    }

    public Lookup getLookup() {
        return lookup;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public boolean isB1() {
        return b1;
    }

    public void setB1(boolean b1) {
        this.b1 = b1;
    }

    public List<LookupItem> getSelectedLookupItems() {
        return selectedLookupItems;
    }

    public void setSelectedLookupItems(List<LookupItem> selectedLookupItems) {
        this.selectedLookupItems = selectedLookupItems;
    }

}
