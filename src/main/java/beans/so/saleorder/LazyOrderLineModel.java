package beans.so.saleorder;

import java.util.List;
import service.AbstractLazyDataModel;
import entities.so.OrderLine;

/**
 *
 * @author savin
 */
public class LazyOrderLineModel extends AbstractLazyDataModel<OrderLine> {

    public LazyOrderLineModel(List<OrderLine> datasource, Class<OrderLine> clazz) throws InstantiationException, IllegalAccessException {
        super(datasource, clazz);
    }

}
