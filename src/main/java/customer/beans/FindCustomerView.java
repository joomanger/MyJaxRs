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
public class FindCustomerView extends AbstractView<Customer> {

    @Inject
    private CustomerCBean client;

//    public FindLookupView() {
//        super(Lookup.class);
//    }

    @Override
    @PostConstruct
    protected void init() {
        super.setEntities(client.findAll());
    }

}
