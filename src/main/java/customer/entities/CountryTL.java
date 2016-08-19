package customer.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author savin
 */
@Entity
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"country_id","language"}))
@NamedQueries(
        @NamedQuery(name = CountryTL.FIND_BY_LANG, query = "select t from CountryTL t where t.language=:p_lang")
)
public class CountryTL implements Serializable {
 public static final String FIND_BY_LANG = "CountryTL.FIND_BY_LANG";
    @Id
    @SequenceGenerator(name = "countrytl_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "countrytl_sq")
    private Long countrytl_id;
    @NotNull
    private String language;
    @Size(min = 3, max = 255,message = "Длина НАЗВАНИЕ от 3 до 255 символов")
    private String name;
    private String description;
   // private String country_id;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Long getCountrytl_id() {
        return countrytl_id;
    }

    public void setCountrytl_id(Long countrytl_id) {
        this.countrytl_id = countrytl_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

//    public String getCountry_id() {
//        return country_id;
//    }
//
//    public void setCountry_id(String country_id) {
//        this.country_id = country_id;
//    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        setCountry(country, true);
    }
     public void setCountry(Country country, boolean set) {
        this.country = country;
        if (country != null && set) {
            country.addCountryTL(this, false);
        }
    }
    
    

}
