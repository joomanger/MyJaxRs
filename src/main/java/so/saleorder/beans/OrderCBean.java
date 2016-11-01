package so.saleorder.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class OrderCBean {

    @Inject
    private OrderEJB ejb;

    /*После выбора нового Заказчика обнуляются все зависимые поля*/
    public void clearFieldsHeader() {
        ejb.clearFieldsHeader();
    }

    public void addNewAttachment() {
        ejb.addNewAttachment();
    }
    public void deleteSelectedAttachment() {
        ejb.deleteSelectedAttachment();
    }

}
