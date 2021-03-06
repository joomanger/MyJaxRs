package beans.customer;

import entities.customer.Customer;
import java.util.List;
import service.AbstractLazyDataModel;

/**
 *
 * @author savin
 */
public class LazyCustomerDataModel extends AbstractLazyDataModel<Customer> {

    public LazyCustomerDataModel(List<Customer> datasource, Class<Customer> clazz) throws InstantiationException, IllegalAccessException {
        super(datasource, clazz);
    }

}
