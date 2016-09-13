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
    private String value;
    private String meaning;
    private String description;

    private Long lookupItem_id;
    private Lookup openedLookup;
    private LookupItem li;

//    public OpenLookupView() {
//        super(Lookup.class);
//    }
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        updateEntityVL();
    }

    public void updateEntityVL() {
        openedLookup = client.find(fls.getLookup_id());
//        for (LookupItem l : openedLookup.getLookupItems()) {
//            for (LookupItem l2 : client.findLookupItemVL(fls.getLookup_id())) {
//                if (l.equals(l2)) {
//                    l.setMeaning(l2.getMeaning());
//                    l.setDescription(l2.getDescription());
//                    break;
//                }
//            }
//        }
        super.setEntity(openedLookup);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Lookup getOpenedLookup() {
        return openedLookup;
    }

    public void setOpenedLookup(Lookup openedLookup) {
        this.openedLookup = openedLookup;
    }
}
