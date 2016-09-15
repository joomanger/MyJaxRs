package customer.beans;

import customer.entities.Customer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author savin
 */
public class LazyCustomerDataModel extends LazyDataModel<Customer> {

    private List<Customer> datasource;

    public LazyCustomerDataModel(List<Customer> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Customer getRowData(String rowKey) {
        for (Customer c : datasource) {
            if (c.getCustomer_id().equals(Long.parseLong(rowKey))) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(Customer c) {
        return c.getCustomer_id();
    }

    @Override
    public List<Customer> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Customer> data = new ArrayList<>();

        //filter
        for (Customer c : datasource) {
            boolean match = true;

            if (filters != null) {

                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        Field f = c.getClass().getDeclaredField(filterProperty);
                        f.setAccessible(true);
                        String fieldValue = String.valueOf(f.get(c));

                        if (filterValue == null || fieldValue.toLowerCase().contains(filterValue.toString().toLowerCase())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        match = false;
                    }
                }
            }

            if (match) {
                data.add(c);
            }
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}
