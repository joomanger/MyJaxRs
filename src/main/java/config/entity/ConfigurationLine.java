package config.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"parameter_id", "header_id"}))
public class ConfigurationLine implements Serializable {

    @Id
    @SequenceGenerator(name = "configurationline_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "configurationline_sq")
    private Long line_id;
    private Integer line_num;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parameter_id")
    private ParameterConfiguration parameter;
    private Long header_id;

    public Long getLine_id() {
        return line_id;
    }

    public void setLine_id(Long line_id) {
        this.line_id = line_id;
    }

    public Integer getLine_num() {
        return line_num;
    }

    public void setLine_num(Integer line_num) {
        this.line_num = line_num;
    }

    public ParameterConfiguration getParameter() {
        return parameter;
    }

    public void setParameter(ParameterConfiguration parameter) {
        this.parameter = parameter;
    }

    public Long getHeader_id() {
        return header_id;
    }

    public void setHeader_id(Long header_id) {
        this.header_id = header_id;
    }

}
