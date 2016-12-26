package entities.so.config;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author savin
 */
@Entity
@XmlRootElement
@Table(uniqueConstraints
        = @UniqueConstraint(columnNames = {"parameter_id", "parameterValue"}))
@NamedQueries({
    //@NamedQuery(name = ParameterConfigurationValues.FIND_BY_HEADER_ID, query = "select b from ParameterConfigurationValues b where b.parameter_id=:p_header_id order by b.line_num"),
    @NamedQuery(name = ParameterConfigurationValues.MAX_LINE_NUM_BY_HEADER_ID, query = "select max(b.line_num) from ParameterConfigurationValues b where b.parameter_id=:p_header_id")})
public class ParameterConfigurationValues implements Serializable {

   // public static final String FIND_BY_HEADER_ID = "PCFBHI";
    public static final String MAX_LINE_NUM_BY_HEADER_ID = "MAX_LINE_NUM";
    @Id
    @SequenceGenerator(name = "parameterConfigurationValues_sq", initialValue = 11, allocationSize = 1)
    @GeneratedValue(generator = "parameterConfigurationValues_sq")
    private long paramater_value_id;
    private Long parameter_id;
    @NotNull
    private int line_num;
    @NotNull
    private String parameterValue;

    public long getParamater_value_id() {
        return paramater_value_id;
    }

    public void setParamater_value_id(long paramater_value_id) {
        this.paramater_value_id = paramater_value_id;
    }

    public Long getParameter_id() {
        return parameter_id;
    }

    public void setParameter_id(Long parameter_id) {
        this.parameter_id = parameter_id;
    }

    public int getLine_num() {
        return line_num;
    }

    public void setLine_num(int line_num) {
        this.line_num = line_num;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

}
