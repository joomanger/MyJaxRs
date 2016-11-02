package so.saleorder.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import so.entities.Attachment;
import so.saleorder.flows.CreateSaleOrderFlow;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class OrderCBean {

    @Inject
    private OrderEJB orderEJB;
    @Inject
    private AttachmentEJB attachEJB;
    @Inject
    private CreateSaleOrderFlow orderFlow;

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
        Attachment a=orderFlow.getAttachment();
        String result = attachEJB.validateMyEntity(a);
        if (result.equals(attachEJB.ERROR)) {
            attachEJB.sendMessage(result, null);
        } else {
            orderEJB.addNewAttachment(a);
            orderFlow.setAttachment(new Attachment());
            orderFlow.setUploadStatus(null);
        }
    }

    public void deleteSelectedAttachment() {
        orderEJB.deleteSelectedAttachment(orderFlow.getSelectedAttachments());
    }

    public void createOrder() {
        orderEJB.createOrder();
    }

}
