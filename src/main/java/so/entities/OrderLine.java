package so.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author savin
 */
@Entity
@Table(name = "zakaz_lines")
public class OrderLine implements Serializable {

    @Id
    @SequenceGenerator(name = "zakaz_line_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "zakaz_line_sq")
    private Long line_id;

    public Long getLine_id() {
        return line_id;
    }

    public void setLine_id(Long line_id) {
        this.line_id = line_id;
    }

}
