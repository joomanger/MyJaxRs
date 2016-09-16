package rw.beans;

import java.util.List;
import rw.entities.RWStation;
import service.AbstractLazyDataModel;

/**
 *
 * @author savin
 */
public class LazyRWSDataModel extends AbstractLazyDataModel<RWStation> {

    public LazyRWSDataModel(List<RWStation> datasource, Class<RWStation> clazz) throws InstantiationException, IllegalAccessException {
        super(datasource, clazz);
    }

}
