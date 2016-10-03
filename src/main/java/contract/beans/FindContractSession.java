package contract.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@SessionScoped
@Secure
public class FindContractSession implements Serializable {

    private Long contract_id;

    public Long getContract_id() {
        return contract_id;
    }

    public void setContract_id(Long contract_id) {
        this.contract_id = contract_id;
    }

}
