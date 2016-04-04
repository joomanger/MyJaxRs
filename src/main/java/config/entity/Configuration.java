package config.entity;

import com.isd.myjaxrs.entity.Item;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
public class Configuration implements Serializable {

    @Id
    @SequenceGenerator(name = "configuration_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "configuration_sq")
    private Long header_id;
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
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
