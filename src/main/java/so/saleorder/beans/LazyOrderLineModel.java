package so.saleorder.beans;

import java.util.List;
import service.AbstractLazyDataModel;
import so.entities.OrderLine;

/**
 *
 * @author savin
 */
public class LazyOrderLineModel extends AbstractLazyDataModel<OrderLine> {

    public LazyOrderLineModel(List<OrderLine> datasource, Class<OrderLine> clazz) throws InstantiationException, IllegalAccessException {
        super(datasource, clazz);
    }

}
