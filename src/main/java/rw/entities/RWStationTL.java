package rw.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"rws_code", "language"}))
@NamedQueries(
        @NamedQuery(name = RWStationTL.FIND_BY_LANG, query = "select t from RWStationTL t where t.language=:p_lang")
)
public class RWStationTL implements Serializable {

    public static final String FIND_BY_LANG = "RWStationTL.FIND_BY_LANG";
    @Id
    @SequenceGenerator(name = "rwstationtl_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "rwstationtl_sq")
    private Long rwstationtl_id;
    @Size(min = 3, max = 255,message = "Длина НАЗВАНИЕ от 3 до 255 символов")
    private String name;
    @Size(min = 2, max = 2)
    private String language;
    @ManyToOne
    @JoinColumn(name = "rws_code")
    private RWStation rwstation;

    public Long getRwstationtl_id() {
        return rwstationtl_id;
    }

    public void setRwstationtl_id(Long rwstationtl_id) {
        this.rwstationtl_id = rwstationtl_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public RWStation getRwstation() {
        return rwstation;
    }

    public void setRwstation(RWStation rwstation) {
        setRWStation(rwstation, true);
    }

    public void setRWStation(RWStation rwstation, boolean set) {
        this.rwstation = rwstation;
        if (rwstation != null && set) {
            rwstation.addRWStationTL(this, false);
        }
    }

}
