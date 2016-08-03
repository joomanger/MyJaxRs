package customer.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"name"}))
public class Customer implements Serializable, Comparable<Customer> {

    @Id
    @SequenceGenerator(name = "customer_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "customer_sq")
    private Long customer_id;
    private String name;
    private String fullName;
    private String okpo;
    private String inn;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "customer")
    @PrivateOwned
    private List<Address> addresses = new ArrayList<>();
    private Boolean activeStatus = true;
    
    public void addAddress(Address adr) {
        addAddress(adr, true);
    }

    public void addAddress(Address adr, boolean add) {
        if (adr != null) {
            getAddresses().add(adr);
            if (add) {
                adr.setCustomer(this, false);
            }
        }
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public int compareTo(Customer o) {
        if (name.charAt(0) > o.name.charAt(0)) {
            return 1;
        } else {
            return -1;
        }
    }

}
