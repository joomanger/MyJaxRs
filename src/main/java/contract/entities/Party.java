package contract.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author savin
 */
@Entity
public class Party implements Serializable {

    @Id
    @SequenceGenerator(name = "party_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "party_sq")
    private Long party_id;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "contract_id")
    private Contract contract;
    //лукапы
    private String role;

    public Long getParty_id() {
        return party_id;
    }

    public void setParty_id(Long party_id) {
        this.party_id = party_id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        setContract(contract, true);
    }

    public void setContract(Contract contract, boolean set) {
        this.contract = contract;
        if (contract != null && set) {
            contract.addParty(this, false);
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
