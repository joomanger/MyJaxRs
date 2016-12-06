package so.config.entity;

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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
public class ParameterConfiguration implements Serializable {

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
    private Short fieldSize;

    private Boolean multiple;

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

    public Short getSize() {
        return size;
    }

    public void setSize(Short size) {
        this.size = size;
    }

    public String getLattribute() {
        return 'l' + attribute;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public Short getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(Short fieldSize) {
        this.fieldSize = fieldSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ParameterConfiguration)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        ParameterConfiguration rhs = (ParameterConfiguration) obj;
        return new EqualsBuilder().
                append(attribute, rhs.attribute).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). 
                append(attribute).
                toHashCode();
    }

}
