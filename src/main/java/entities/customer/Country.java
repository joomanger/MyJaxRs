package entities.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author savin
 */
@Entity
public class Country implements Serializable {

    @Id
    @Size(min = 2, max = 2, message = "Значение КОД должно быть 2-буквенным")
    @Column(length = 2)
    private String country_id;

    @Size(min = 0, max = 3, message = "Значение EU-код должно быть не более 3-х символов")
    private String eu_code;
    @Size(min = 3, max = 3, message = "Значение ISO-код должно быть 3-буквенным")
    private String iso_code;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "country")
    @PrivateOwned
    @OrderBy("language asc")
    private List<CountryTL> countryTL = new ArrayList<>();
//    @Transient
//    private String name;
//
//    public Country() {
//    }
//
//    public Country(String country_id, String eu_code, String iso_code, List<CountryTL> countryTL) {
//        this.country_id = country_id;
//        this.eu_code = eu_code;
//        this.iso_code = iso_code;
//        this.countryTL = countryTL;
//    }
    public CountryTL getTranslateObject(String lang) {
        for (CountryTL tl : getCountryTL()) {
            if (tl.getLanguage().equals(lang)) {
                return tl;
            }
        }
        return null;
    }
    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
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

    public List<CountryTL> getCountryTL() {
        return countryTL;
    }

    public void setCountryTL(List<CountryTL> countryTL) {
        this.countryTL = countryTL;
    }

    public void addCountryTL(CountryTL countryTL) {
        addCountryTL(countryTL, true);
    }

    public void addCountryTL(CountryTL countryTL, boolean add) {
        if (countryTL != null) {
            getCountryTL().add(countryTL);

            if (add) {
                countryTL.setCountry(this, false);
            }
        }
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

}
