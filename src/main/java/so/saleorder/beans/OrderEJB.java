package so.saleorder.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB;
import so.entities.Attachment;
import so.entities.Order;

/**
 *
 * @author savin
 */
@Stateless
public class OrderEJB extends AbstractEJB<Order> {

    private Order order;

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public OrderEJB() {
        super(Order.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void addNewAttachment(Attachment attachment) {
        order.addAttachment(attachment);
    }

    public void deleteSelectedAttachment(List<Attachment> attachments) {
        order.getAttachments().removeAll(attachments);
    }

    public void createOrder() {
        super.create(order);
    }

}
