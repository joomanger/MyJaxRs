package customer.beans;

import customer.entities.Address;
import customer.entities.Country;
import customer.entities.Customer;
import customer.entities.RWAddress;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWStation;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewCustomerView extends AbstractView<Customer> {

//    @Inject
//    private CustomerCBean client;
    @Inject
    private FindCustomerSession fls;
//    @Inject
//    private SessionActions sa;

    private Customer entity;

    private List<Address> selectedAddresses = new ArrayList<>();
    //private List<RWAddress> selectedRWAddresses = new ArrayList<>();
    private List<RWAddress> filteredRWAddresses;
    private List<Address> filteredAddresses;

    //Поля для создания новой строки ЖД адреса
    private RWStation station;
    private String rwBranch;
    private String rwRcvCode;
    //Поля для создания новой строки юр. адреса
    private Country country;
    private String region;
    private String region2;
    private String postCode;
    private String city;
    private String fullAddress;
    private Boolean ship_to;
    private Boolean bill_to;
    private Boolean vendor;
    private Boolean activeStatus;
    private Long duferco_site_use_id;

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        entity = new Customer();

//        List<RWAddress> rw = entity.getRWAddresses();
//
//        Collections.sort(rw, new Comparator<RWAddress>() {
//            @Override
//            public int compare(RWAddress o1, RWAddress o2) {
//                return o1.getStation().getTranslateObject(sa.getLanguage()).getName().
//                        compareTo(o2.getStation().getTranslateObject(sa.getLanguage()).getName());
//            }
//        });
        super.setEntity(entity);
    }
    

    public RWStation getStation() {
        return station;
    }

    public void setStation(RWStation station) {
        this.station = station;
    }

    public String getRwBranch() {
        return rwBranch;
    }

    public void setRwBranch(String rwBranch) {
        this.rwBranch = rwBranch;
    }

    public String getRwRcvCode() {
        return rwRcvCode;
    }

    public void setRwRcvCode(String rwRcvCode) {
        this.rwRcvCode = rwRcvCode;
    }

    public List<Address> getSelectedAddresses() {
        return selectedAddresses;
    }

    public void setSelectedAddresses(List<Address> selectedAddresses) {
        this.selectedAddresses = selectedAddresses;
    }

    public List<RWAddress> getFilteredRWAddresses() {
        return filteredRWAddresses;
    }

    public void setFilteredRWAddresses(List<RWAddress> filteredRWAddresses) {
        this.filteredRWAddresses = filteredRWAddresses;
    }

    public List<Address> getFilteredAddresses() {
        return filteredAddresses;
    }

    public void setFilteredAddresses(List<Address> filteredAddresses) {
        this.filteredAddresses = filteredAddresses;
    }

//    public List<RWAddress> getSelectedRWAddresses() {
//        return selectedRWAddresses;
//    }
//
//    public void setSelectedRWAddresses(List<RWAddress> selectedRWAddresses) {
//        this.selectedRWAddresses = selectedRWAddresses;
//    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Boolean getShip_to() {
        return ship_to;
    }

    public void setShip_to(Boolean ship_to) {
        this.ship_to = ship_to;
    }

    public Boolean getBill_to() {
        return bill_to;
    }

    public void setBill_to(Boolean bill_to) {
        this.bill_to = bill_to;
    }

    public Boolean getVendor() {
        return vendor;
    }

    public void setVendor(Boolean vendor) {
        this.vendor = vendor;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Long getDuferco_site_use_id() {
        return duferco_site_use_id;
    }

    public void setDuferco_site_use_id(Long duferco_site_use_id) {
        this.duferco_site_use_id = duferco_site_use_id;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getRegion2() {
        return region2;
    }

    public void setRegion2(String region2) {
        this.region2 = region2;
    }

    public void clearAddressFields() {
        fls.setRegion(null);
        region2 = null;
        postCode = null;
        city = null;
        fullAddress = null;
        ship_to = false;
        bill_to = false;
        vendor = false;
        activeStatus = false;
        duferco_site_use_id = null;
    }

}
