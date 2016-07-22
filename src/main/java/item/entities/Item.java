package item.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
public class Item implements Serializable, Comparable<Item> {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "item_sq", initialValue = 6, allocationSize = 1)
    @GeneratedValue(generator = "item_sq")
    private long item_id;
    private String name;
    private String description;
    private String uom1;
    private String uom2;

//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    @JoinColumn(name = "uom3")
//    private LookupItem uom3 = new LookupItem();
    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
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

    public String getUom1() {
        return uom1;
    }

    public void setUom1(String uom1) {
        this.uom1 = uom1;
    }

    public String getUom2() {
        return uom2;
    }

    public void setUom2(String uom2) {
        this.uom2 = uom2;
    }

    @Override
    public int compareTo(Item o) {
        if (this.name.charAt(0) > o.name.charAt(0)) {
            return 1;
        } else {
            return -1;
        }
    }

}
