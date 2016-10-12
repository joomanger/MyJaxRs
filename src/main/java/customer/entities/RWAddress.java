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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import rw.entities.RWStation;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"customer_id", "rws_code", "rwRcvCode", "rwbranch"}))
public class RWAddress implements Serializable {

    @Id
    @SequenceGenerator(name = "rwaddress_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "rwaddress_sq")
    private Long rwaddress_id;
    private Long rwaddress_id_orc;
    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "rws_code")
    @NotNull(message = "Поле ЖД Станция обязательна для заполнения")
    private RWStation station;
    private String rwbranch;
    private String rwrcvcode;
    private Boolean activeStatus = true;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getRwaddress_id() {
        return rwaddress_id;
    }

    public void setRwaddress_id(Long rwaddress_id) {
        this.rwaddress_id = rwaddress_id;
    }

    public RWStation getStation() {
        return station;
    }

    public void setStation(RWStation station) {
        this.station = station;
    }

    public String getRwbranch() {
        return rwbranch;
    }

    public void setRwbranch(String rwbranch) {
        this.rwbranch = rwbranch;
    }

    public String getRwrcvcode() {
        return rwrcvcode;
    }

    public void setRwrcvcode(String rwrcvcode) {
        this.rwrcvcode = rwrcvcode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        setCustomer(customer, true);
    }

    public Long getRwaddress_id_orc() {
        return rwaddress_id_orc;
    }

    public void setRwaddress_id_orc(Long rwaddress_id_orc) {
        this.rwaddress_id_orc = rwaddress_id_orc;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setCustomer(Customer customer, boolean set) {
        this.customer = customer;
        if (customer != null && set) {
            customer.addRWAddress(this, false);
        }
    }

    @Transient
    public String getRWAddressLabel(String lang) {
        return getStation().getTranslateObject(lang).getName()
                + ((getRwrcvcode() == null) ? "" : "/" + getRwrcvcode())
                + ((getRwbranch() == null) ? "" : "/" + getRwbranch());
    }

}
