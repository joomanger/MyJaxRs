package customer.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
        = @UniqueConstraint(columnNames = {"name", "language"}))
@NamedQueries(
        @NamedQuery(name = Country.FIND_BY_LANG, query = "select t from Country t where t.language=:p_lang order by t.name")
)
public class Country implements Serializable, Comparable<Country> {

    public static final String FIND_BY_LANG = "Country.FIND_BY_LANG";
    @Id
    @SequenceGenerator(name = "country_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "country_sq")
    private Long country_id;
    @NotNull
    private String name;
    @NotNull
    private String fullName;
    @NotNull
    @Size(min = 2, max = 2, message = "Значение КОД должно быть 2-буквенным")
    private String code;
    @NotNull
    @Size(min = 0, max = 3, message = "Значение КОД должно быть не более 3-х символов")
    private String eu_code;
    @Size(min = 3, max = 3, message = "Значение ISO-код должно быть 3-буквенным")
    private String iso_code;

    private String language;

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEu_code() {
        return eu_code;
    }

    public void setEu_code(String eu_code) {
        this.eu_code = eu_code;
    }

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int compareTo(Country o) {
        if (this.name.charAt(0) > o.name.charAt(0)) {
            return 1;
        } else {
            return -1;
        }
    }

}
