package rw.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author savin
 */
@Entity
public class RWStation implements Serializable {

    @Id
    protected String rws_code;
    private Long location_id_orc;
    @OneToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "rwroad_code")
    protected RWRoad rwroad;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "rwstation")
    @PrivateOwned
    @OrderBy("language asc")
    private List<RWStationTL> rwstationTL = new ArrayList<>();
    @Transient
    private String fullName;

    public RWStation() {
    }

    public RWStation(String rws_code, Long location_id_orc, RWRoad rwroad, List<RWStationTL> rwstationTL) {
        this.rws_code = rws_code;
        this.rwroad = rwroad;
        this.rwstationTL = rwstationTL;
        this.location_id_orc = location_id_orc;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getLocation_id_orc() {
        return location_id_orc;
    }

    public void setLocation_id_orc(Long location_id_orc) {
        this.location_id_orc = location_id_orc;
    }

    public String getRws_code() {
        return rws_code;
    }

    public void setRws_code(String rws_code) {
        this.rws_code = rws_code;
    }

    public RWRoad getRwroad() {
        return rwroad;
    }

    public void setRwroad(RWRoad rwroad) {
        this.rwroad = rwroad;
    }

    public List<RWStationTL> getRwstationTL() {
        return rwstationTL;
    }

    public void setRwstationTL(List<RWStationTL> rwstationTL) {
        this.rwstationTL = rwstationTL;
    }

    public void addRWStationTL(RWStationTL rwstationTL) {
        addRWStationTL(rwstationTL, true);
    }

    public void addRWStationTL(RWStationTL rwstationTL, boolean add) {
        if (rwstationTL != null) {
            getRwstationTL().add(rwstationTL);
            if (add) {
                rwstationTL.setRWStation(this, false);
            }
        }
    }

}
