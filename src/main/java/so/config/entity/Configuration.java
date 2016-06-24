package so.config.entity;

import item.entities.Item;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"item_id", "config_ver_num"}))
@NamedQueries({
    @NamedQuery(name = Configuration.FIND_LAST_VERSION_BY_ITEM, query = "select max(c.config_ver_num) from Configuration c where c.item.id=:p_item_id"),
    @NamedQuery(name = Configuration.FIND_HEADER_ID_BY_LAST_VER_NUM_AND_ITEM_ID, query = "select c from Configuration c where c.item.id=:p_item_id and c.config_ver_num=:p_ver_num")})
public class Configuration implements Serializable {

    public static final String FIND_LAST_VERSION_BY_ITEM = "Q1";
    public static final String FIND_HEADER_ID_BY_LAST_VER_NUM_AND_ITEM_ID = "Q2";
    @Id
    @SequenceGenerator(name = "configuration_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "configuration_sq")
    private Long header_id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "header_id")
    private List<ConfigurationLine> lines;

    private Integer config_ver_num;

    public Long getHeader_id() {
        return header_id;
    }

    public void setHeader_id(Long header_id) {
        this.header_id = header_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<ConfigurationLine> getLines() {
        return lines;
    }

    public void setLines(List<ConfigurationLine> lines) {
        this.lines = lines;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getConfig_ver_num() {
        return config_ver_num;
    }

    public void setConfig_ver_num(Integer config_ver_num) {
        this.config_ver_num = config_ver_num;
    }

}
