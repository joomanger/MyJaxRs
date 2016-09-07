package customer.beans;

import customer.entities.Customer;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class CustomerCBean extends AbstractClientBean<Customer> {

    @Inject
    private CustomerEJB ejb;

    @Inject
    private FindCustomerView flv;

    @Inject
    private NewCustomerView nlv;

    @Inject
    private OpenCustomerView olv;

    @Override
    protected AbstractEJB<Customer> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<Customer> getOpenView() {
        return olv;
    }

    @Override
    protected AbstractView<Customer> getFindView() {
        return flv;
    }

    @Override
    protected AbstractView<Customer> getNewView() {
        return nlv;
    }

    //----------------------//
    //Дополнительные методы 
    //----------------------//
//    public Lookup findByName(String name) {
//        return ejb.findByName(name);
//    }
//
//    public void addLookupItemNLV() {
//
//        LookupItem li = new LookupItem();
//        
//        li.setLookupItem_id(itemEJB.getNextLookupItemID());
//        li.setValuez(nlv.getValue());
//        li.setActiveStatus(Boolean.TRUE);
//        li.setMeaning(nlv.getMeaning());
//        li.setDescription(nlv.getDescription());
//
//        String result = itemEJB.validateMyEntity(li);
//        if (!result.equals(itemEJB.SUCCESSFUL)) {
//            itemEJB.sendMessage(result, null);
//            return;
//        }
//
//        for (String l : sa.getSystemLanguages()) {
//            LookupItemTL tl = new LookupItemTL();
//            tl.setLanguage(l);
//            tl.setMeaning(nlv.getMeaning());
//            tl.setDescription(nlv.getDescription());
//            result = itemEJBTL.validateMyEntity(tl);
//            if (!result.equals(itemEJBTL.SUCCESSFUL)) {
//                itemEJBTL.sendMessage(result, null);
//                return;
//            }
//            li.addLookupItemTL(tl);
//        }
//
//        nlv.getEntity().addLookupItem(li);
//        nlv.setValue(null);
//        nlv.setMeaning(null);
//        nlv.setDescription(null);
//        //nlv.updateEntityVL();
//    }
//
//    public void deleteLookupItemsNLV() {
//        nlv.getEntity().getLookupItems().removeAll(nlv.getSelectedEntityLines());
//    }
//
    public void addRWAddressOCV(){
//        LookupItem li = new LookupItem();
//        li.setValuez(olv.getValue());
//
//        String result = itemEJB.validateMyEntity(li);
//        if (!result.equals(itemEJB.SUCCESSFUL)) {
//            itemEJB.sendMessage(result, null);
//            return;
//        }
//
//        for (String l : sa.getSystemLanguages()) {
//            LookupItemTL tl = new LookupItemTL();
//            tl.setLanguage(l);
//            tl.setMeaning(olv.getMeaning());
//            tl.setDescription(olv.getDescription());
//            result = itemEJBTL.validateMyEntity(tl);
//            if (!result.equals(itemEJBTL.SUCCESSFUL)) {
//                itemEJBTL.sendMessage(result, null);
//                return;
//            }
//            li.addLookupItemTL(tl);
//        }
//
//        olv.getEntity().addLookupItem(li);
//        olv.setValue(null);
//        olv.setMeaning(null);
//        olv.setDescription(null);
//
//        changeEntity();
    }
//
//    public void deleteLookupItemsOLV() {
//        for (Object li : olv.getSelectedEntityLines()) {
//            if (li instanceof LookupItem) {
//                olv.getEntity().getLookupItems().remove((LookupItem) li);
//            }
//        }
//        changeEntity();
//    }
//
//    @Override
//    public void deleteSelectedEntities() {
////        for (Iterator<Lookup> lookupIter = flv.getSelectedEntities().iterator(); lookupIter.hasNext();) {
////            Lookup lookup = lookupIter.next();
////            if (lookup.getSystemLookup()) {
////                lookupIter.remove();
////                ejb.sendMessage("Системный объект удалить нельзя", null);
////            }
////        }
//        flv.getEntities().removeAll(flv.getSelectedEntities());
//        super.deleteSelectedEntities();
//    }
//
//    public List<LookupItem> findLookupItemVL(Long p_lookup_id) {
//        return ejb.findLookupItemVL(p_lookup_id);
//    }
//    
//    
//    @Override
//    public void changeEntity() {
//        super.changeEntity();
//        olv.updateEntityVL();
//    }
}
