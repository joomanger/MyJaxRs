package payment.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
        = @UniqueConstraint(columnNames = {"name"}))
public class Payment implements Serializable, Comparable<Payment> {

    @Id
    @SequenceGenerator(name = "payment_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "payment_sq")
    private Long payment_id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String prepayShip;
    @NotNull
    private String prepayManuf;
    @NotNull
    private Integer prepayValue;
    private Integer delayDaysAfterShp;
    private String dayType;

    public Long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrepayShip() {
        return prepayShip;
    }

    public void setPrepayShip(String prepayShip) {
        this.prepayShip = prepayShip;
    }

    public String getPrepayManuf() {
        return prepayManuf;
    }

    public void setPrepayManuf(String prepayManuf) {
        this.prepayManuf = prepayManuf;
    }

    public Integer getPrepayValue() {
        return prepayValue;
    }

    public void setPrepayValue(Integer prepayValue) {
        this.prepayValue = prepayValue;
    }

    public Integer getDelayDaysAfterShp() {
        return delayDaysAfterShp;
    }

    public void setDelayDaysAfterShp(Integer delayDaysAfterShp) {
        this.delayDaysAfterShp = delayDaysAfterShp;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
    }

    @Override
    public int compareTo(Payment o) {
        if (this.name.charAt(0) > o.name.charAt(0)) {
            return 1;
        } else {
            return -1;
        }
    }

}
