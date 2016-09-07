package customer.beans;

import customer.entities.Customer;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lookup.entities.LookupItem;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewCustomerView extends AbstractView<Customer> {

    private final Customer entity = new Customer();

    //Поля для создания новой строки
    private String value;
    private String meaning;
    private String description;

    private Long lookupItem_id;
    private LookupItem li;

//    public NewLookupView() {
//        super(Lookup.class);
//    }

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        entity.setActiveStatus(Boolean.TRUE);
        super.setEntity(entity);
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
//        if (lookupItem_id != null) {
//            for (LookupItem j : getEntity().getLookupItems()) {
//                if (j.getLookupItem_id().equals(lookupItem_id)) {
//                    li = j;
//                    break;
//                }
//            }
//        }
    }

    public LookupItem getLi() {
        return li;
    }

    public void setLi(LookupItem li) {
        this.li = li;
    }

    public void updateEntityVL() {
        //openedLookup = client.find(fls.getLookup_id());
//        for (LookupItem l : getEntity().getLookupItems()) {
//            for (LookupItemTL tl : li.getLookupItemTL()) {
//                if (l.getLookupItem_id().equals(tl.getLookupItem().getLookupItem_id())) {
//                    l.setMeaning(tl.getMeaning());
//                    l.setDescription(tl.getDescription());
//                    break;
//                }
//            }
//        }
        //super.setEntity(openedLookup);
    }

}
