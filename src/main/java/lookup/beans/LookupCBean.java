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

    @Inject
    private OpenLookupView olv;

    public List<Lookup> findAll() {
        return ejb.findAll();
    }

    public Lookup find(Long lookup_id) {
        return ejb.find(lookup_id);
    }

    public void saveLookup() {
        Lookup lp = olv.getLookup();
        String result = ejb.validateMyEntity(lp);
        if (result.equals(ejb.SUCCESSFUL)) {
            String status = ejb.edit(lp);
            ejb.sendMessage(status, "Справочник обновлен успешно");
            if (!status.equals(ejb.SUCCESSFUL)) {
                ejb.sendMessage(status, null);
            }
        } else {
            ejb.sendMessage(result, null);
        }
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
        String result = ejb.validateMyEntity(lookup);
        if (result.equals(ejb.SUCCESSFUL)) {
            String status = ejb.create(lookup);
            ejb.sendMessage(status, "Справочник создан успешно");
            if (status.equals(ejb.SUCCESSFUL)) {
                return "lookups";
            } else {
                ejb.sendMessage(status, null);
                return null;
            }
        } else {
            ejb.sendMessage(result, null);
            return null;
        }

    }

    public void addlookupItemOMV() {
        LookupItem li = new LookupItem();
        li.setValuez(olv.getLookupValue());
        li.setValuezDescription(olv.getLookupValueDescription());
        String result = itemEJB.validateMyEntity(li);
        if (result.equals(itemEJB.SUCCESSFUL)) {
            li.setLookup(olv.getLookup());
            String status = itemEJB.create(li);
            if (status.equals(itemEJB.SUCCESSFUL)) {
                itemEJB.sendMessage(status, "Значение добавлено успешно");
                olv.setLookupValue(null);
                olv.setLookupValueDescription(null);
            } else {
                itemEJB.sendMessage(status, null);
            }
        } else {
            itemEJB.sendMessage(result, null);
        }

    }

    public void deleteLookupItemsOMV() {
        olv.getLookup().getLookupItems().removeAll(olv.getSelectedLookupItems());
        saveLookup();
    }
}
