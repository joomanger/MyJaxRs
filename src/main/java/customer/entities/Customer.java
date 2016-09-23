package customer.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"name"}))
public class Customer implements Serializable {

    @Id
    @SequenceGenerator(name = "customer_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "customer_sq")
    private Long customer_id;
    private Long customer_id_orc;
    @Size(min = 2, message = "НАИМЕНОВАНИЕ обязательно для заполнения")
    private String name;
    @Size(min = 2, message = "ПОЛНОЕ НАИМЕНОВАНИЕ обязательно для заполнения")
    private String fullName;
    private String okpo;
    private String inn;
    private Boolean resident;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "customer", fetch = FetchType.LAZY)
    @PrivateOwned
    private List<Address> addresses = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "customer", fetch = FetchType.LAZY)
    @PrivateOwned
    private List<RWAddress> RWAddresses = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "customer", fetch = FetchType.LAZY)
    @PrivateOwned
    private List<Relationship> relationships = new ArrayList<>();

    private Boolean activeStatus = true;

    public Customer() {
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    public void addRelationship(Relationship rls) {
        addRelationship(rls, true);
    }

    public void addRelationship(Relationship rls, boolean add) {
        if (rls != null) {
            getRelationships().add(rls);
            if (add) {
                rls.setCustomer(this, false);
            }
        }
    }

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

    public void addRWAddress(RWAddress adr) {
        addRWAddress(adr, true);
    }

    public void addRWAddress(RWAddress adr, boolean add) {
        if (adr != null) {
            getRWAddresses().add(adr);
            if (add) {
                adr.setCustomer(this, false);
            }
        }
    }

    public List<RWAddress> getRWAddresses() {
        return RWAddresses;
    }

    public void setRWAddresses(List<RWAddress> RWAddresses) {
        this.RWAddresses = RWAddresses;
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

    public Long getCustomer_id_orc() {
        return customer_id_orc;
    }

    public void setCustomer_id_orc(Long customer_id_orc) {
        this.customer_id_orc = customer_id_orc;
    }

    public Boolean getResident() {
        return resident;
    }

    public void setResident(Boolean resident) {
        this.resident = resident;
    }

}
