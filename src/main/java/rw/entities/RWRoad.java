package rw.entities;

import customer.entities.Country;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
        = @UniqueConstraint(columnNames = {"name"}))
public class RWRoad implements Serializable, Comparable<RWRoad> {

    @Id
    @SequenceGenerator(name = "rwroad_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "rwroad_sq")
    private Long rwr_id;
    @NotNull
    @Size(min = 2, max = 3, message = "Значение ISO-код должно быть 2- или 3- значным")
    private String rwr_code;
    @NotNull
    private String shortName;
    @NotNull
    private String name;
    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "country_fk")
    @NotNull
    private Country country;

    public Long getRwr_id() {
        return rwr_id;
    }

    public void setRwr_id(Long rwr_id) {
        this.rwr_id = rwr_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int compareTo(RWRoad o) {
        if (this.name.charAt(0) > o.name.charAt(0)) {
            return 1;
        } else {
            return -1;
        }
    }

}
