package customer.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author savin
 */
@Entity
public class Address implements Serializable {

    @Id
    @SequenceGenerator(name = "address_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "address_sq")
    private Long address_id;
    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "country_fk")
    @NotNull
    private Country country;
    private String region;
    @NotNull
    private String city;
    @NotNull
    private String fullAddress;
    private String postCode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Boolean bill_to = true;
    private Boolean ship_to = true;
    private Boolean isVendor;
    private String rcv_code;

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

    public Boolean getBill_to() {
        return bill_to;
    }

    public void setBill_to(Boolean bill_to) {
        this.bill_to = bill_to;
    }

    public Boolean getShip_to() {
        return ship_to;
    }

    public void setShip_to(Boolean ship_to) {
        this.ship_to = ship_to;
    }

    public Boolean getIsVendor() {
        return isVendor;
    }

    public void setIsVendor(Boolean isVendor) {
        this.isVendor = isVendor;
    }

    public String getRcv_code() {
        return rcv_code;
    }

    public void setRcv_code(String rcv_code) {
        this.rcv_code = rcv_code;
    }

}
