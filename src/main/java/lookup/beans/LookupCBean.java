package lookup.beans;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.entities.Lookup;
import lookup.entities.LookupItem;
import lookup.entities.LookupItemVL;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class LookupCBean extends AbstractClientBean<Lookup> {

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

    public LookupCBean() {
        super(Lookup.class);
    }

    @Override
    protected AbstractEJB<Lookup> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<Lookup> getOpenView() {
        return olv;
    }

    @Override
    protected AbstractView<Lookup> getFindView() {
        return flv;
    }

    @Override
    protected AbstractView<Lookup> getNewView() {
        return nlv;
    }

    @Override
    public List<Lookup> findAll() {
        List<Lookup> l = super.findAll();
        Collections.sort(l);
        return l;
    }

    //----------------------//
    //Дополнительные методы 
    //----------------------//
    public Lookup findByName(String name) {
        return ejb.findByName(name);
    }

    public void addLookupItemNLV() {
        Lookup l = nlv.getEntity();
        LookupItem li = new LookupItem();
        li.setValuez(nlv.getNewLookupName());
//        li.setValuezDescription(nlv.getNewLookupDesc());
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

    public void deleteLookupItemsNLV() {
        nlv.getEntity().getLookupItems().removeAll(nlv.getSelectedEntityLines());
    }

    public void addlookupItemOLV() {
        LookupItem li = new LookupItem();
        li.setValuez(olv.getLookupValue());
        // li.setValuezDescription(olv.getLookupValueDescription());
        String result = itemEJB.validateMyEntity(li);
        if (result.equals(itemEJB.SUCCESSFUL)) {
            li.setLookup(olv.getEntity());
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

    public void deleteLookupItemsOLV() {

        for (Object li : olv.getSelectedEntityLines()) {
            if (li instanceof LookupItemVL) {
                String result = itemEJB.remove(((LookupItemVL) li).getLookupItem());
                if (!result.equals(itemEJB.SUCCESSFUL)) {
                    itemEJB.sendMessage(result, null);
                }
            }
        }
        olv.updateEntityVL();
    }

    @Override
    public void deleteSelectedEntities() {
        for (Iterator<Lookup> lookupIter = flv.getSelectedEntities().iterator(); lookupIter.hasNext();) {
            Lookup lookup = lookupIter.next();
            if (lookup.getSystemLookup()) {
                lookupIter.remove();
                ejb.sendMessage("Системный объект удалить нельзя", null);
            }
        }
        super.deleteSelectedEntities();
    }

    public List<LookupItemVL> findLookupItemVL(Long p_lookup_id) {
        return ejb.findLookupItemVL(p_lookup_id);
    }

    @Override
    public void changeEntity() {
        super.changeEntity();
        olv.updateEntityVL();
    }
}
