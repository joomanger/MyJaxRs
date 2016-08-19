package rw.entities;

import customer.entities.Country;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"fullName"}))
public class RWRoad implements Serializable, Comparable<RWRoad> {

    @Id
    @Size(min = 2, max = 3, message = "Значение КОД должно быть 2- или 3- значным")
    private String rwr_code;
    @Size(min = 1, max = 255,message = "Значение СОКР.НАИМЕНОВАНИЕ обязательно")
    private String shortName;
    @Size(min = 1, max = 255,message = "Значение ПОЛНОЕ НАИМЕНОВАНИЕ обязательно")
    private String fullName;
    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "country_id")
    @NotNull(message = "Значение СТРАНА обязательно")
    private Country country;

    public RWRoad() {
    }

    public RWRoad(String rwr_code, String shortName, String fullName, Country country) {
        this.rwr_code = rwr_code;
        this.shortName = shortName;
        this.fullName = fullName;
        this.country = country;
    }

    public String getRwr_code() {
        return rwr_code;
    }

    public void setRwr_code(String rwr_code) {
        this.rwr_code = rwr_code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getNameForReport() {
        return shortName + "(" + rwr_code + ")";
    }

    @Override
    public int compareTo(RWRoad o) {
        try {
            if (this.fullName.charAt(0) > o.fullName.charAt(0)) {
                return 1;
            } else if (this.fullName.charAt(0) == o.fullName.charAt(0)) {
                return 0;
            } else {
                return -1;
            }
        } catch (Exception ex) {
            return 0;
        }
    }

}
