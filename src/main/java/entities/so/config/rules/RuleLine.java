package entities.so.config.rules;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import service.WhoIS;

/**
 *
 * @author savin
 */
@Entity
@Table(name = "rule_lines", uniqueConstraints
        = @UniqueConstraint(columnNames = {"header_id,line_number"}))
public class RuleLine implements Serializable, WhoIS {

    @Id
    @SequenceGenerator(name = "rule_line_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "rule_line_sq")
    private Long line_id;
    private Short line_number;
    @NotNull
    @Size(max = 1500, message = "Максимальная длина формулы 1500 символов")
    @Column(length = 1500)
    private String formula;
    @Column(name = "result_attribute")

    //@NotNull
    private String resultAttribute;
    @Column(name = "result_condition", length = 150)
    @Size(max = 150, message = "Максимальная длина условия 150 символов")
    private String resultCondition;

    @ManyToOne
    @JoinColumn(name = "header_id", foreignKey = @ForeignKey(name = "line_rule_fk", value = ConstraintMode.CONSTRAINT,
            foreignKeyDefinition = "FOREIGN KEY (header_id) REFERENCES public.rules(header_id) MATCH SIMPLE\n"
            + "      ON UPDATE CASCADE ON DELETE CASCADE"))
    private Rule rule;

    /*WHO*/
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    @NotNull
    private Date creationDate;
    @Column(name = "created_by")
    @NotNull
    private Long createdBy;
    @Temporal(TemporalType.DATE)
    @Column(name = "last_update_date")
    private Date lastUpdateDate;
    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    public void setRule(Rule rule) {
        setRule(rule, true);
    }

    public void setRule(Rule rule, boolean set) {
        this.rule = rule;
        if (rule != null && set) {
            rule.addLine(this, false);
        }
    }

    public Long getLine_id() {
        return line_id;
    }

    public void setLine_id(Long line_id) {
        this.line_id = line_id;
    }

    public Short getLine_number() {
        return line_number;
    }

    public void setLine_number(Short line_number) {
        this.line_number = line_number;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getResultAttribute() {
        return resultAttribute;
    }

    public void setResultAttribute(String resultAttribute) {
        this.resultAttribute = resultAttribute;
    }

    public String getResultCondition() {
        return resultCondition;
    }

    public void setResultCondition(String resultCondition) {
        this.resultCondition = resultCondition;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public Long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Override
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Rule getRule() {
        return rule;
    }

}
