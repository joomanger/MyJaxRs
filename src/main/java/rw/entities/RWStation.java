/*

 */
package rw.entities;

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

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"name"}))
public class RWStation implements Serializable, Comparable<RWStation> {

    @Id
    @SequenceGenerator(name = "rwstation_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "rwstation_sq")
    private Long rws_id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "rwroad_fk")
    @NotNull
    private RWRoad rwroad;

    public Long getRws_id() {
        return rws_id;
    }

    public void setRws_id(Long rws_id) {
        this.rws_id = rws_id;
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

    public RWRoad getRwroad() {
        return rwroad;
    }

    public void setRwroad(RWRoad rwroad) {
        this.rwroad = rwroad;
    }

    @Override
    public int compareTo(RWStation o) {
        if (this.name.charAt(0) > o.name.charAt(0)) {
            return 1;
        } else {
            return -1;
        }
    }

}
