package lookup.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.entities.Lookup;
import lookup.entities.LookupItem;
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

    private Long lookupItem_id;
    private LookupItem li;

    public OpenLookupView() {
        super(Lookup.class);
    }

    @PostConstruct
    @Override
    protected void init() {
        Lookup l = client.find(fls.getLookup_id());
        l.setLookupItemsVL(client.findLookupItemVL(fls.getLookup_id()));
        super.setEntity(l);
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

    public Long getLookupItem_id() {
        return lookupItem_id;
    }

    public void setLookupItem_id(Long lookupItem_id) {
        this.lookupItem_id = lookupItem_id;
        if (lookupItem_id != null) {
            for (LookupItem j : getEntity().getLookupItems()) {
                if (j.getLookupItem_id().equals(lookupItem_id)) {
                    li = j;
                    break;
                }
            }
        }
    }

    public LookupItem getLi() {
        return li;
    }

    public void setLi(LookupItem li) {
        this.li = li;
    }

}
