package customer.beans;

import customer.entities.Customer;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class FindCustomerView extends AbstractView<Customer> {

    @Inject
    private CustomerCBean client;
    
    private LazyDataModel<Customer> lazyModel;

    @Override
    @PostConstruct
    protected void init() {
        lazyModel=new LazyCustomerDataModel(client.findAll());
        //super.setEntities(client.findAll());
    }

    public LazyDataModel<Customer> getLazyModel() {
        return lazyModel;
    }
 

}
