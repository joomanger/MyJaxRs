package customer.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"rws_code","rwRcvCode"}))
public class RWAddress implements Serializable, Comparable<RWAddress> {

    @Id
    @SequenceGenerator(name = "rwaddress_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "rwaddress_sq")
    private Long rwaddress_id;

    private String rws_code;
    private String rwBranch;
    private String rwRcvCode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getRwaddress_id() {
        return rwaddress_id;
    }

    public void setRwaddress_id(Long rwaddress_id) {
        this.rwaddress_id = rwaddress_id;
    }

    public String getRws_code() {
        return rws_code;
    }

    public void setRws_code(String rws_code) {
        this.rws_code = rws_code;
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

    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        setCustomer(customer, true);
    }

    public void setCustomer(Customer customer, boolean set) {
        this.customer = customer;
        if (customer != null && set) {
            customer.addRWAddress(this, false);
        }
    }

    @Override
    public int compareTo(RWAddress o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
