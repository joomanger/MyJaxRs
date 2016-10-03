package customer.beans;

import customer.entities.Customer;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import service.AbstractView;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class FindCustomerView extends AbstractView<Customer> {

    @Inject
    private CustomerCBean client;

    private LazyDataModel<Customer> lazyModel;

    @Override
    @PostConstruct
    protected void init() {
        updateLazyDataModel();
    }

    public void updateLazyDataModel() {
        try {
            lazyModel = new LazyCustomerDataModel(client.findAll(), Customer.class);
        } catch (IllegalAccessException | InstantiationException ex) {
            System.out.println(ex);
        }
    }

    public LazyDataModel<Customer> getLazyModel() {
        return lazyModel;
    }

}
