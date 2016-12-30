package entities.so.config.rules;

import entities.item.Item;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.PrivateOwned;
import service.WhoIS;

/**
 *
 * @author savin
 */
@Entity
@Table(name = "rules", uniqueConstraints
        = @UniqueConstraint(columnNames = {"item_id","name"}))
public class Rule implements Serializable,WhoIS {

    @Id
    @SequenceGenerator(name = "rule_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "rule_sq")
    private Long header_id;
    @Size(max = 150, message = "Максимальная длина названия 150 символов")
    @Column(length = 150)
    private String name;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "rule", fetch = FetchType.LAZY)
    @PrivateOwned
    @OrderBy("line_number asc")
    private List<RuleLine> lines = new ArrayList<>();
    
    private Boolean activeStatus=true;

    /*WHO*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    @NotNull
    private Date creationDate;
    @Column(name = "created_by")
    @NotNull
    private Long createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_date")
    @NotNull
    private Date lastUpdateDate;
    @Column(name = "last_updated_by")
    @NotNull
    private Long lastUpdatedBy;

    @ManyToOne
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Item item;

    public Long getHeader_id() {
        return header_id;
    }

    public void setHeader_id(Long header_id) {
        this.header_id = header_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RuleLine> getLines() {
        return lines;
    }

    public void setLines(List<RuleLine> lines) {
        this.lines = lines;
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

    public void addLine(RuleLine line) {
        addLine(line, true);
    }

    public void addLine(RuleLine line, boolean add) {
        if (line != null) {
            getLines().add(line);
            if (add) {
                line.setRule(this, false);
            }
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
    
}
