package entities.customer;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author savin
 */
@Entity
@NamedQueries(
        @NamedQuery(name = Address.FIND_BY_CUSTOMER_ID, query = "select t from Address t where t.customer.customer_id=:p_customer_id"))
public class Address implements Serializable {

    public static final String FIND_BY_CUSTOMER_ID = "Address.FIND_BY_CUSTOMER_ID";
    @Id
    @SequenceGenerator(name = "address_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "address_sq")
    private Long address_id;

    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "country_id")
    @NotNull(message = "поле Страна обязательна для заполнения")
    private Country country;
    private String region;

    @Size(min = 2, message = "поле Город обязательно для заполнения")
    private String city;

    @Size(min = 2, message = "поле Адрес обязательно для заполнения")
    private String fullAddress;
    private String postCode;

    private Boolean ship_to;
    private Boolean bill_to;
    private Boolean vendor;
    private Boolean activeStatus;

    private String shipuses_id;
    private String billuses_id;

    private Long duferco_site_use_id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        setCustomer(customer, true);
    }

    public void setCustomer(Customer customer, boolean set) {
        this.customer = customer;
        if (customer != null && set) {
            customer.addAddress(this, false);
        }
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
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

    public String getShipuses_id() {
        return shipuses_id;
    }

    public void setShipuses_id(String shipuses_id) {
        this.shipuses_id = shipuses_id;
    }

    public String getBilluses_id() {
        return billuses_id;
    }

    public void setBilluses_id(String billuses_id) {
        this.billuses_id = billuses_id;
    }

    public Long getDuferco_site_use_id() {
        return duferco_site_use_id;
    }

    public void setDuferco_site_use_id(Long duferco_site_use_id) {
        this.duferco_site_use_id = duferco_site_use_id;
    }

    @Transient
    public String getAddressLabel(String lang) {
        return getCountry().getTranslateObject(lang).getName()
                + ((getPostCode() == null) ? "" : "/"+getPostCode()) 
                + ((getRegion() == null) ? "" : "/"+getRegion()) 
                + ((getCity() == null) ? "" : "/"+getCity())
                + ((getFullAddress() == null) ? "" : "/"+getFullAddress());
    }

}
