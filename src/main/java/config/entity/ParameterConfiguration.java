package config.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
public class ParameterConfiguration implements Serializable, Comparable<ParameterConfiguration> {

    public enum ParameterType {
        INTEGER,
        DOUBLE,
        STRING,
        TABLE
    }

    @Id
    @SequenceGenerator(name = "parameterConfiguration_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "parameterConfiguration_sq")
    private Long parameter_id;
    @Column(unique = true)
    @NotNull(message = "Обязательно для заполнения")
    private String name;
    private String columnName;
    private String attribute;
    private ParameterType parameterType;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "parameter_id")
    private List<ParameterConfigurationValues> values;
    
    private Short size;

    public Long getParameter_id() {
        return parameter_id;
    }

    public void setParameter_id(Long parameter_id) {
        this.parameter_id = parameter_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public void setParameterType(ParameterType parameterType) {
        this.parameterType = parameterType;
    }

    @XmlTransient
    public List<ParameterConfigurationValues> getValues() {
        return values;
    }

    public void setValues(List<ParameterConfigurationValues> values) {
        this.values = values;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public int compareTo(ParameterConfiguration p_parameter) {
        Byte a = Byte.parseByte(this.getAttribute().toLowerCase().replace("attribute", ""));
        Byte b = Byte.parseByte(p_parameter.getAttribute().toLowerCase().replace("attribute", ""));
        if (a > b) {
            return 1;
        } else if (a == b) {
            return 0;
        } else {
            return -1;
        }

    }

    public Short getSize() {
        return size;
    }

    public void setSize(Short size) {
        this.size = size;
    }
    
    public String getLattribute() {
        return 'l'+attribute;
    }
    
    
    
    

}
