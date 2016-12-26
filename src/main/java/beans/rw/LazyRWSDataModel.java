package beans.rw;

import java.util.List;
import entities.rw.RWStationVL;
import service.AbstractLazyDataModel;

/**
 *
 * @author savin
 */
public class LazyRWSDataModel extends AbstractLazyDataModel<RWStationVL> {

    public LazyRWSDataModel(List<RWStationVL> datasource, Class<RWStationVL> clazz) throws InstantiationException, IllegalAccessException {
        super(datasource, clazz);
    }

}
