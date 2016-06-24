package lookup.beans;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.entities.Lookup;
import lookup.entities.LookupItem;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class LookupCBean {

    @Inject
    private LookupEJB ejb;

    @Inject
    private LookupItemEJB itemEJB;

    @Inject
    private FindLookupView flv;

    @Inject
    private NewLookupView nlv;

    public List<Lookup> findAll() {
        return ejb.findAll();
    }

    public void deleteLookups() {
        for (Lookup lookup : flv.getSelectedLookups()) {
            String status = ejb.remove(lookup);
            ejb.sendMessage(status, "Справочник " + lookup.getName() + " удален успешно");
            flv.getLookups().remove(lookup);
        }

    }

    public void addLookupItemNLV() {

        Lookup l = nlv.getLookup();

        LookupItem li = new LookupItem();

        li.setValuez(nlv.getNewLookupName());
        li.setValuezDescription(nlv.getNewLookupDesc());
        li.setActiveStatus(Boolean.TRUE);

        String result = itemEJB.validateMyEntity(li);
        if (result.equals(itemEJB.SUCCESSFUL)) {
            l.addLookupItem(li);
            nlv.setNewLookupName(null);
            nlv.setNewLookupDesc(null);
        } else {
            itemEJB.sendMessage(result, null);
        }

    }

    public String createLookup() {
        Lookup lookup = nlv.getLookup();
        String status = ejb.create(lookup);
        ejb.sendMessage(status, "Справочник создан успешно");
        if (status.equals(ejb.SUCCESSFUL)) {
            return "lookups";
        } else {
            ejb.sendMessage(status, null);
            return null;
        }
    }

}
