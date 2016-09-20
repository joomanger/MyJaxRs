package customer.beans;

import customer.entities.Address;
import customer.entities.Customer;
import customer.entities.RWAddress;
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

    public void deleteRWAddressesOV() {
        olv.getEntity().getRWAddresses().removeAll(olv.getSelectedEntityLines());
        changeEntity();
    }

    @Override
    public void deleteSelectedEntities() {
        super.deleteSelectedEntities();
        flv.updateLazyDataModel();
    }

}
