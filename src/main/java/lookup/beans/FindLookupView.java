package lookup.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.entities.Lookup;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class FindLookupView implements Serializable {

    @Inject
    private LookupCBean client;
    
    private List<Lookup> lookups = new ArrayList<>();
    private List<Lookup> selectedLookups = new ArrayList<>();
    
    @PostConstruct
    private void init() {
        lookups = client.findAll();
    }

    public List<Lookup> getLookups() {
        return lookups;
    }

    public void setLookups(List<Lookup> lookups) {
        this.lookups = lookups;
    }

    public List<Lookup> getSelectedLookups() {
        return selectedLookups;
    }

    public void setSelectedLookups(List<Lookup> selectedLookups) {
        this.selectedLookups = selectedLookups;
    }

}
