package so.config.entity;

import java.io.Serializable;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author savin
 */
@Entity
@Table(name = "configuration_line", uniqueConstraints
        = @UniqueConstraint(columnNames = {"parameter_id", "header_id"}))
@NamedQueries({
    @NamedQuery(name = ConfigurationLine.FIND_BY_HEADER_ID, query = "select t from ConfigurationLine t where t.configuration.header_id=:p_header_id order by t.line_num"),
    @NamedQuery(name = ConfigurationLine.MAX_LINE_NUM_BY_HEADER_ID, query = "select max(t.line_num) from ConfigurationLine t where t.configuration.header_id=:p_header_id")}
)
public class ConfigurationLine implements Serializable {

    public static final String FIND_BY_HEADER_ID = "BHI";
    public static final String MAX_LINE_NUM_BY_HEADER_ID = "MBHI";
    @Id
    @SequenceGenerator(name = "configurationline_sq", initialValue = 13, allocationSize = 1)
    @GeneratedValue(generator = "configurationline_sq")
    private Long line_id;
    private Integer line_num;
    @ManyToOne
    @JoinColumn(name = "parameter_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @NotNull(message = "Параметр обязателен для заполнения")
    private ParameterConfiguration parameter;

    @ManyToOne
    @JoinColumn(name = "header_id", foreignKey = @ForeignKey(name = "conf_confline_fk", value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (header_id) REFERENCES public.configuration(header_id) MATCH SIMPLE\n"
            + "      ON UPDATE CASCADE ON DELETE CASCADE"))
    private Configuration configuration;

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

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        setConfiguration(configuration, true);
    }

    public void setConfiguration(Configuration configuration, boolean set) {
        this.configuration = configuration;
        if (configuration != null && set) {
            configuration.addLine(this, false);
        }
    }

}
