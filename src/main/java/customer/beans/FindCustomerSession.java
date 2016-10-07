package customer.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@SessionScoped
public class FindCustomerSession implements Serializable {

    private Long customer_id;
    private Long shpCustomer_id;
    private Long invCustomer_id;
    private String region;

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getShpCustomer_id() {
        return shpCustomer_id;
    }

    public void setShpCustomer_id(Long shpCustomer_id) {
        this.shpCustomer_id = shpCustomer_id;
    }

    public Long getInvCustomer_id() {
        return invCustomer_id;
    }

    public void setInvCustomer_id(Long invCustomer_id) {
        this.invCustomer_id = invCustomer_id;
    }

}
