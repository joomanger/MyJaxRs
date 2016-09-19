package rw.beans;

import java.util.List;
import rw.entities.RWStationVL;
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
