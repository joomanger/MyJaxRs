package config.entity;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
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
    @Column(unique=true)
    @NotNull(message = "Обязательно для заполнения")
    private String name;
    private String description;
    private ParameterType parameterType;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "parameter_id")
    private List<ParameterConfigurationValues> values;

    public long getParameter_id() {
        return parameter_id;
    }

    public void setParameter_id(long parameter_id) {
        this.parameter_id = parameter_id;
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

}
