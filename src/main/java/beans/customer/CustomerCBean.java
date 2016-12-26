package beans.customer;

import entities.customer.Address;
import entities.customer.Customer;
import entities.customer.RWAddress;
import entities.customer.Relationship;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    private RWAddressEJB rwAddrEJB;

    @Inject
    private AddressEJB addrEJB;

    @Inject
    private RelationshipEJB rlsEJB;

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
    public List<Customer> getRelCustomersByCustomerID(Long p_customer_id, Boolean p_ship_to, Boolean p_bill_to) {
        try {
            return ejb.getRelCustomersByCustomerID(p_customer_id, p_ship_to, p_bill_to);
        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> l = super.findAll();
        Collections.sort(l, new Comparator() {
            @Override
            public int compare(Object l1, Object l2) {
                return ((Customer) l1).getName().compareTo(((Customer) l2).getName());
            }
        });
        return l;
    }

    public void deleteAddressesOV() {
        olv.getEntity().getAddresses().removeAll(olv.getSelectedAddresses());
        changeEntity();
    }

    public void deleteAddressesNV() {
        nlv.getEntity().getAddresses().removeAll(nlv.getSelectedAddresses());

    }

    public void addAddressesNV() {
        Address ar = new Address();
        ar.setActiveStatus(Boolean.TRUE);
        ar.setBill_to(nlv.getBill_to());
        ar.setShip_to(nlv.getShip_to());
        ar.setVendor(nlv.getVendor());
        ar.setFullAddress(nlv.getFullAddress());
        ar.setDuferco_site_use_id(nlv.getDuferco_site_use_id());
        ar.setCity(nlv.getCity());
        ar.setCountry(nlv.getCountry());
        ar.setRegion(nlv.getRegion2());
        ar.setPostCode(nlv.getPostCode());
        String validation = addrEJB.validateMyEntity(ar);
        if (validation.equals(addrEJB.SUCCESSFUL)) {
            nlv.getEntity().addAddress(ar);
        } else {
            addrEJB.sendMessage(validation, null);
        }

    }

    public void addAddressesOV() {
        Address ar = new Address();
        ar.setActiveStatus(Boolean.TRUE);
        ar.setBill_to(olv.getBill_to());
        ar.setShip_to(olv.getShip_to());
        ar.setVendor(olv.getVendor());
        ar.setFullAddress(olv.getFullAddress());
        ar.setDuferco_site_use_id(olv.getDuferco_site_use_id());
        ar.setCity(olv.getCity());
        ar.setCountry(olv.getCountry());
        ar.setRegion(olv.getRegion2());
        ar.setPostCode(olv.getPostCode());
        String validation = addrEJB.validateMyEntity(ar);
        if (validation.equals(addrEJB.SUCCESSFUL)) {
            ar.setCustomer(olv.getEntity());
            String result = addrEJB.create(ar);
            if (result.equals(addrEJB.SUCCESSFUL)) {
                addrEJB.sendMessage(result, "Адрес добавлен успешно");
            } else {
                addrEJB.sendMessage(result, null);
            }
            olv.setCountry(null);
            olv.clearAddressFields();
        } else {
            addrEJB.sendMessage(validation, null);
        }
    }

    public void addRWAddressOV() {
        RWAddress rw = new RWAddress();
        rw.setActiveStatus(Boolean.TRUE);
        rw.setRwbranch(olv.getRwBranch());
        rw.setRwrcvcode(olv.getRwRcvCode());
        rw.setStation(olv.getStation());
        String validation = rwAddrEJB.validateMyEntity(rw);
        if (validation.equals(ejb.SUCCESSFUL)) {
            rw.setCustomer(olv.getEntity());
            //olv.getEntity().addRWAddress(rw);
            String result = rwAddrEJB.create(rw);
            if (result.equals(rwAddrEJB.SUCCESSFUL)) {
                rwAddrEJB.sendMessage(result, "ЖД Адрес добавлен успешно");
            } else {
                rwAddrEJB.sendMessage(result, null);
            }
            olv.setStation(null);
            olv.setRwBranch(null);
            olv.setRwRcvCode(null);
        } else {
            ejb.sendMessage(validation, null);
        }
    }

    public void addRWAddressNV() {
        RWAddress rw = new RWAddress();
        rw.setActiveStatus(Boolean.TRUE);
        rw.setRwbranch(nlv.getRwBranch());
        rw.setRwrcvcode(nlv.getRwRcvCode());
        rw.setStation(nlv.getStation());
        String validation = rwAddrEJB.validateMyEntity(rw);
        if (validation.equals(ejb.SUCCESSFUL)) {
            nlv.getEntity().addRWAddress(rw);
            nlv.setStation(null);
            nlv.setRwBranch(null);
            nlv.setRwRcvCode(null);
        } else {
            ejb.sendMessage(validation, null);
        }
    }

    public void deleteRWAddressesOV() {
        olv.getEntity().getRWAddresses().removeAll(olv.getSelectedEntityLines());
        changeEntity();
    }

    public void deleteRWAddressesNV() {
        nlv.getEntity().getRWAddresses().removeAll(olv.getSelectedEntityLines());
    }

    @Override
    public void deleteSelectedEntities() {
        super.deleteSelectedEntities();
        flv.updateLazyDataModel();
    }

    public void deleteRelsOV() {
        olv.getEntity().getRelationships().removeAll(olv.getSelectedRelationships());
        changeEntity();
    }

    public void deleteRelsNV() {
        nlv.getEntity().getRelationships().removeAll(nlv.getSelectedRelationships());
    }

    public void addRelsOV() {
        Relationship rls = new Relationship();
        rls.setActiveStatus(Boolean.TRUE);
        rls.setRelatedCustomer(olv.getRelatedCustomer());
        rls.setBill_to(olv.getBill_to2());
        rls.setShip_to(olv.getShip_to2());
        String validation = rlsEJB.validateMyEntity(rls);
        if (validation.equals(rlsEJB.SUCCESSFUL)) {
            rls.setCustomer(olv.getEntity());
            String result = rlsEJB.create(rls);
            if (result.equals(rlsEJB.SUCCESSFUL)) {
                rlsEJB.sendMessage(result, "Отношение добавлено успешно");
            } else {
                rlsEJB.sendMessage(result, null);
            }
        } else {
            rlsEJB.sendMessage(validation, null);
        }
    }

    public void addRelsNV() {
        Relationship rls = new Relationship();
        rls.setActiveStatus(Boolean.TRUE);
        rls.setRelatedCustomer(nlv.getRelatedCustomer());
        rls.setBill_to(nlv.getBill_to2());
        rls.setShip_to(nlv.getShip_to2());
        String validation = rlsEJB.validateMyEntity(rls);
        if (validation.equals(rlsEJB.SUCCESSFUL)) {
            rls.setCustomer(nlv.getEntity());
        } else {
            rlsEJB.sendMessage(validation, null);
        }
    }

}
