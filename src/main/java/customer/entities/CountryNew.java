/*

 */
package customer.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import so.entities.SaleOrderLine;

/**
 *
 * @author savin
 */
@Entity
@NamedQueries(
        @NamedQuery(name = CountryNew.FIND_ALL, query = "select t from CountryNew t")
)
public class CountryNew implements Serializable {
public static final String FIND_ALL = "CountryNew.FIND_ALL";
    @Id
    private String country_id;
   //@NotNull
//    @Size(min = 0, max = 3, message = "Значение КОД должно быть не более 3-х символов")
    private String eu_code;
  //  @Size(min = 3, max = 3, message = "Значение ISO-код должно быть 3-буквенным")
    private String iso_code;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "country_id")
    private List<CountryTL> countryTL;

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

}
