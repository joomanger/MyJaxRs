package entities.contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"name"}))
public class Contract implements Serializable {

    @Id
    @SequenceGenerator(name = "contract_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "contract_sq")
    private Long contract_id;
    private Long contract_id_orc;
    @Size(min = 1,message = "поле НОМЕР обязательно для заполнения")
    private String contractNumber;
    @NotNull(message = "поле ГРУППА обязательно для заполнения")
    private String contractGroup;
    @NotNull(message = "поле ЦЕЛЬ обязательно для заполнения")
    private String purpose;
    @NotNull(message = "поле ВАЛЮТА обязательно для заполнения")
    private String currency;

    private Double amount;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "поле ДАТА НАЧАЛА обязательно для заполнения")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "contract", fetch = FetchType.LAZY)
    @PrivateOwned
    private List<Party> parties = new ArrayList<>();

    public Long getContract_id() {
        return contract_id;
    }

    public void setContract_id(Long contract_id) {
        this.contract_id = contract_id;
    }

    public Long getContract_id_orc() {
        return contract_id_orc;
    }

    public void setContract_id_orc(Long contract_id_orc) {
        this.contract_id_orc = contract_id_orc;
    }

    public String getContractGroup() {
        return contractGroup;
    }

    public void setContractGroup(String contractGroup) {
        this.contractGroup = contractGroup;
    }

    
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    public void addParty(Party p) {
        addParty(p, true);
    }

    public void addParty(Party p, boolean add) {
        if (p != null) {
            getParties().add(p);
            if (add) {
                p.setContract(this, false);
            }
        }
    }

}
