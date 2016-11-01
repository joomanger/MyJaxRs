package so.saleorder.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB;
import so.entities.Attachment;
import so.entities.Order;
import so.saleorder.flows.CreateSaleOrderFlow;

/**
 *
 * @author savin
 */
@Stateless
public class OrderEJB extends AbstractEJB<Order> {

    @Inject
    private CreateSaleOrderFlow orderFlow;
    @Inject
    private AttachmentEJB attachEJB;

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public OrderEJB() {
        super(Order.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*После выбора нового Заказчика обнуляются все зависимые поля*/
    public void clearFieldsHeader() {
        orderFlow.getOrder().setShp_customer(null);
        orderFlow.getOrder().setShp_address(null);
        orderFlow.getOrder().setShp_rwaddress(null);
        orderFlow.getOrder().setInv_customer(null);
        orderFlow.getOrder().setInv_address(null);
        orderFlow.getOrder().setContract(null);
    }

    public void addNewAttachment() {
        String result = attachEJB.validateMyEntity(orderFlow.getAttachment());
        if (result.equals(attachEJB.ERROR)) {
            attachEJB.sendMessage(result, null);
        } else {
            orderFlow.getOrder().getAttachments().add(orderFlow.getAttachment());
            orderFlow.setAttachment(new Attachment());
            orderFlow.setUploadStatus(null);
        }
    }

    public void deleteSelectedAttachment() {
        orderFlow.getOrder().getAttachments().removeAll(orderFlow.getSelectedAttachments());
    }
    
    public void createOrder(){
        create(orderFlow.getOrder());
    }

}
