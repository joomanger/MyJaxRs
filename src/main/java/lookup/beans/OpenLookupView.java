package lookup.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.entities.Lookup;
import lookup.entities.LookupItem;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenLookupView implements Serializable {

    @Inject
    private LookupCBean client;
    @Inject
    private FindLookupSession fls;

    private Lookup lookup;
   //private LookupItem lookupItem;
    private String lookupValue;
    private String lookupValueDescription;
            
    private List<LookupItem> selectedLookupItems = new ArrayList<>();
    private List<Integer> linesForSave = new ArrayList<>();

    @PostConstruct
    private void init() {
        lookup = client.find(fls.getLookup_id());
        //lookupItem = new LookupItem();
    }

    public Lookup getLookup() {
        return lookup;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public List<LookupItem> getSelectedLookupItems() {
        return selectedLookupItems;
    }

    public void setSelectedLookupItems(List<LookupItem> selectedLookupItems) {
        this.selectedLookupItems = selectedLookupItems;
    }

//    public void onCellEdit(CellEditEvent event) {
//        linesForSave.add(event.getRowIndex());
//    }
//
//    public void onCellEdit(AjaxBehaviorEvent event) {
//        if (event.getComponent() instanceof SelectBooleanCheckbox) {
//            SelectBooleanCheckbox c = (SelectBooleanCheckbox) event.getComponent();
//
//            if (c.getAttributes().get("lookupItem") instanceof MenuItem) {
//                LookupItem li = (LookupItem) (c.getAttributes().get("lookupItem"));
//                //System.out.println("menu.getMenuItems().indexOf(rw)=" + menu.getMenuItems().indexOf(rw));
//                linesForSave.add(lookup.getLookupItems().indexOf(li));
//            }
//        }
//
//    }

    public List<Integer> getLinesForSave() {
        return linesForSave;
    }

    public void setLinesForSave(List<Integer> linesForSave) {
        this.linesForSave = linesForSave;
    }

//    public LookupItem getLookupItem() {
//        return lookupItem;
//    }
//
//    public void setLookupItem(LookupItem lookupItem) {
//        this.lookupItem = lookupItem;
//    }

    public String getLookupValue() {
        return lookupValue;
    }

    public void setLookupValue(String lookupValue) {
        this.lookupValue = lookupValue;
    }

    public String getLookupValueDescription() {
        return lookupValueDescription;
    }

    public void setLookupValueDescription(String lookupValueDescription) {
        this.lookupValueDescription = lookupValueDescription;
    }
    
    

}
