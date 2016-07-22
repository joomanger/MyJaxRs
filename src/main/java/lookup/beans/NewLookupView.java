package lookup.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lookup.entities.Lookup;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewLookupView extends AbstractView<Lookup> {

    private final Lookup lookup = new Lookup();
    
    private String newLookupName;
    private String newLookupDesc;

    public NewLookupView() {
        super(Lookup.class);
    }

    @PostConstruct
    @Override
    protected void init() {
        lookup.setActiveStatus(Boolean.TRUE);
        super.setEntity(lookup);
    }

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

}
