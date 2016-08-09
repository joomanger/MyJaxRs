package customer.entities;

import java.io.Serializable;
import javax.persistence.ColumnResult;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;

/**
 *
 * @author savin
 */
@SqlResultSetMapping(
        name = "CountryMapping",
        entities = @EntityResult(
                entityClass = CountryNew.class,
                fields = {
                    @FieldResult(name = "country_id", column = "country_id"),
                    @FieldResult(name = "name", column = "name"),
                    @FieldResult(name = "description", column = "description")})
//        columns = {
//            @ColumnResult(name = "name", type = String.class),
//            @ColumnResult(name = "description", type = String.class)}
)
public class CountryVL implements Serializable {

    private String country_id;
    private String name;
    private String description;

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
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
