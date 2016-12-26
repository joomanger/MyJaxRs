package beans.customer;

import entities.customer.Address;
import entities.customer.Country;
import entities.customer.Customer;
import entities.customer.RWAddress;
import entities.customer.Relationship;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entities.rw.RWStation;
import service.AbstractView;
import service.Secure;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class OpenCustomerView extends AbstractView<Customer> {

    @Inject
    private CustomerCBean client;
    @Inject
    private FindCustomerSession fls;
    @Inject
    private SessionActions sa;

    private Customer openedEntity = new Customer();

    private List<Address> selectedAddresses = new ArrayList<>();
    private List<Address> selectedRelationships = new ArrayList<>();
    private List<RWAddress> filteredRWAddresses;
    private List<Address> filteredAddresses;
    private List<Address> filteredRelationships;

    //Поля для создания новой строки ЖД адреса
    private RWStation station;
    private String rwBranch;
    private String rwRcvCode;
    //Поля для создания новой строки юр. адреса
    private Country country;
    private String region2;
    private String postCode;
    private String city;
    private String fullAddress;
    private Boolean ship_to;
    private Boolean bill_to;
    private Boolean vendor;
    private Boolean activeStatus;
    private Long duferco_site_use_id;
    //Поля для создания отношения
    private Customer relatedCustomer;
    private Boolean ship_to2;
    private Boolean bill_to2;
    private Boolean activeStatus2;

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        openedEntity = client.find(fls.getCustomer_id());

        List<RWAddress> rw = openedEntity.getRWAddresses();
        List<Relationship> rl = openedEntity.getRelationships();

        Collections.sort(rw, new Comparator<RWAddress>() {
            @Override
            public int compare(RWAddress o1, RWAddress o2) {
                return o1.getStation().getTranslateObject(sa.getLanguage()).getName().
                        compareTo(o2.getStation().getTranslateObject(sa.getLanguage()).getName());
            }
        });
        Collections.sort(rl, new Comparator<Relationship>() {
            @Override
            public int compare(Relationship o1, Relationship o2) {
                return o1.getRelatedCustomer().getName().
                        compareTo(o2.getRelatedCustomer().getName());
            }
        });

        super.setEntity(openedEntity);
    }

    public Customer getOpenedEntity() {
        return openedEntity;
    }

    public void setOpenedEntity(Customer openedEntity) {
        this.openedEntity = openedEntity;
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

    public List<Address> getSelectedRelationships() {
        return selectedRelationships;
    }

    public void setSelectedRelationships(List<Address> selectedRelationships) {
        this.selectedRelationships = selectedRelationships;
    }

    public List<Address> getFilteredRelationships() {
        return filteredRelationships;
    }

    public void setFilteredRelationships(List<Address> filteredRelationships) {
        this.filteredRelationships = filteredRelationships;
    }

    public Customer getRelatedCustomer() {
        return relatedCustomer;
    }

    public void setRelatedCustomer(Customer relatedCustomer) {
        this.relatedCustomer = relatedCustomer;
    }

    public Boolean getShip_to2() {
        return ship_to2;
    }

    public void setShip_to2(Boolean ship_to2) {
        this.ship_to2 = ship_to2;
    }

    public Boolean getBill_to2() {
        return bill_to2;
    }

    public void setBill_to2(Boolean bill_to2) {
        this.bill_to2 = bill_to2;
    }

    public Boolean getActiveStatus2() {
        return activeStatus2;
    }

    public void setActiveStatus2(Boolean activeStatus2) {
        this.activeStatus2 = activeStatus2;
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

    public void clearRelsFields() {
        ship_to2 = null;
        bill_to2 = null;
        relatedCustomer = null;
    }

}
