package entities.payment;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"name"}))
public class PaymentTerm implements Serializable {

    @Id
    @SequenceGenerator(name = "paymentterm_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "paymentterm_sq")
    private Long payment_id;
    private Long payment_id_orc;
    @NotNull
    @Size(min = 5, max = 255, message = "Размер УСЛОВИЯ от 5 до 255 символов")
    private String condition;
    @NotNull
    private Boolean prepayShip = false;
    @NotNull
    private Boolean prepayManuf = false;
    @NotNull
    @Max(value = 100, message = "Предоплата не должна превышать 100%")
    @Min(value = 0, message = "Предоплата не должна быть меньше 0%")
    private Integer prepayValue;
    private Integer delayDaysAfterShp;
    @Size(min = 0, max = 15)
    private String dayType;

    public Long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    public Long getPayment_id_orc() {
        return payment_id_orc;
    }

    public void setPayment_id_orc(Long payment_id_orc) {
        this.payment_id_orc = payment_id_orc;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Boolean getPrepayShip() {
        return prepayShip;
    }

    public void setPrepayShip(Boolean prepayShip) {
        this.prepayShip = prepayShip;
    }

    public Boolean getPrepayManuf() {
        return prepayManuf;
    }

    public void setPrepayManuf(Boolean prepayManuf) {
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

}
