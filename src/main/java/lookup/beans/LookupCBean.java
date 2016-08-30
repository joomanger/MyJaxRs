package lookup.beans;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.entities.Lookup;
import lookup.entities.LookupItem;
import lookup.entities.LookupItemTL;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;
import service.SessionActions;

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
    private LookupItemTLEJB itemEJBTL;

    @Inject
    private FindLookupView flv;

    @Inject
    private NewLookupView nlv;

    @Inject
    private OpenLookupView olv;

    @Inject
    private SessionActions sa;

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
        li.setValuez(olv.getValue());

        String result = itemEJB.validateMyEntity(li);
        if (!result.equals(itemEJB.SUCCESSFUL)) {
            itemEJB.sendMessage(result, null);
            return;
        }

        for (String l : sa.getSystemLanguages()) {
            LookupItemTL tl = new LookupItemTL();
            tl.setLanguage(l);
            tl.setMeaning(olv.getMeaning());
            tl.setDescription(olv.getDescription());
            result = itemEJBTL.validateMyEntity(tl);
            if (!result.equals(itemEJBTL.SUCCESSFUL)) {
                itemEJBTL.sendMessage(result, null);
                return;
            }
            li.addLookupItemTL(tl);
        }

        olv.getEntity().addLookupItem(li);
        olv.setValue(null);
        olv.setMeaning(null);
        olv.setDescription(null);

        changeEntity();

//        String val = ejb.validateMyEntity(olv.getEntity());
//        if (val.equals(itemEJB.SUCCESSFUL)) {
//            String result = ejb.edit(olv.getEntity());
//            if (result.equals(ejb.SUCCESSFUL)) {
//                ejb.sendMessage(result, "Значение добавлено успешно");
//                olv.setLookupValue(null);
//                olv.setLookupValueDescription(null);
//                olv.updateEntityVL();
//            } else {
//                ejb.sendMessage(result, null);
//            }
//        } else {
//            ejb.sendMessage(val, null);
//        }
        //ejb.getEntityManager().refresh(olv.getEntity());
//        System.out.println("-----After add items----");
//        test();
    }

    public void deleteLookupItemsOLV() {

        for (Object li : olv.getSelectedEntityLines()) {
            if (li instanceof LookupItem) {
                olv.getEntity().getLookupItems().remove((LookupItem) li);
            }
        }
        //olv.setSelectedEntityLines(null);
        changeEntity();
//        System.out.println("-----After delete items----");
//        test();
    }

    public void test() {
        System.out.println("SIZE LookupItem: " + olv.getOpenedLookup().getLookupItems().size());
        for (LookupItem m : olv.getOpenedLookup().getLookupItems()) {
            System.out.println(m.getValuez());
        }
//        System.out.println("SIZE LookupItemVL: " + olv.getOpenedLookup().getLookupItemsVL().size());
//        for (LookupItemVL m : olv.getOpenedLookup().getLookupItemsVL()) {
//            System.out.println(m.getValuez());
//        }
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

    public List<LookupItem> findLookupItemVL(Long p_lookup_id) {
        return ejb.findLookupItemVL(p_lookup_id);
    }

    @Override
    public void changeEntity() {
        super.changeEntity();
        olv.updateEntityVL();
    }
}
