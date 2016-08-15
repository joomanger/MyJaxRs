package customer.entities;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

/**
 *
 * @author savin
 */
//@SqlResultSetMapping(
//        name = "CountryVLMapping",
//        classes = @ConstructorResult(
//                targetClass = CountryVL.class,
//                columns = {
//                    @ColumnResult(name = "country_id"),
//                    @ColumnResult(name = "name"),
//                    @ColumnResult(name = "description"),
//                    @ColumnResult(name = "eu_code"),
//                    @ColumnResult(name = "iso_code")
//                }))
public class CountryVL extends Country {

    private String name;
    private String description;

    public CountryVL() {
    }

    public CountryVL(String country_id, String name, String description, String eu_code, String iso_code) {
        this.country_id = country_id;
        this.name = name;
        this.description = description;
        this.eu_code = eu_code;
        this.iso_code = iso_code;
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

}
