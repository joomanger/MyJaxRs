package entities.customer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"customer_id", "related_customer_id"}))
public class Relationship {

    @Id
    @SequenceGenerator(name = "rls_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "rls_sq")
    private Long rls_id;

    private Boolean bill_to;
    private Boolean ship_to;
    private Boolean activeStatus;
    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "related_customer_id")
    @NotNull(message = "Связанный клиент обязателен для заполнения")
    private Customer relatedCustomer;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getRls_id() {
        return rls_id;
    }

    public void setRls_id(Long rls_id) {
        this.rls_id = rls_id;
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

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Customer getRelatedCustomer() {
        return relatedCustomer;
    }

    public void setRelatedCustomer(Customer relatedCustomer) {
        this.relatedCustomer = relatedCustomer;
    }

    public void setCustomer(Customer customer) {
        setCustomer(customer, true);
    }

    public void setCustomer(Customer customer, boolean set) {
        this.customer = customer;
        if (customer != null && set) {
            customer.addRelationship(this, false);
        }
    }

}
