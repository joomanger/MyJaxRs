package customer.beans;

import customer.entities.Customer;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenCustomerView extends AbstractView<Customer> {

    @Inject
    private CustomerCBean client;
    @Inject
    private FindCustomerSession fls;

    private Customer openedEntity;
    //Поля для создания новой строки

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        openedEntity = client.find(fls.getCustomer_id());
        super.setEntity(openedEntity);
    }

    public Customer getOpenedEntity() {
        return openedEntity;
    }

    public void setOpenedEntity(Customer openedEntity) {
        this.openedEntity = openedEntity;
    }

}
