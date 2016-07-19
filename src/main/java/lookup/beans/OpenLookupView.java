package lookup.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.entities.Lookup;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenLookupView extends AbstractView<Lookup> {
    
    @Inject
    private LookupCBean client;
    @Inject
    private FindLookupSession fls;
    
    //Поля для создания новой строки
    private String lookupValue;
    private String lookupValueDescription;

    public OpenLookupView() {
        super(Lookup.class);
    }
    
    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(client.find(fls.getLookup_id()));
    }

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
