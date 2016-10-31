package so.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author savin
 */
@Entity
public class Attachment implements Serializable {

    @Id
    @SequenceGenerator(name = "attachment_sq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "attachment_sq")
    private Long attachment_id;
    @ManyToOne
    @JoinColumn(name = "header_id")
    private Order order;
    @Column(name = "category_name", length = 50)
    private String categoryName;
    @Column(name = "category_text", length = 2000)
    private String categoryText;
    @Column(name = "file_name", length = 25)
    private String fileName;
    @Column(name = "line_number")
    private Integer lineNumber;

    public void setOrder(Order order) {
        setOrder(order, true);
    }

    public void setOrder(Order order, boolean set) {
        this.order = order;
        if (order != null && set) {
            order.addAttachment(this, false);
        }
    }

    public Long getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(Long attachment_id) {
        this.attachment_id = attachment_id;
    }

    public Order getOrder() {
        return order;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryText() {
        return categoryText;
    }

    public void setCategoryText(String categoryText) {
        this.categoryText = categoryText;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

}
