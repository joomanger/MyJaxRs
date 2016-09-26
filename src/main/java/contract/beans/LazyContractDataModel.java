package contract.beans;

import contract.entities.Contract;
import java.util.List;
import service.AbstractLazyDataModel;

/**
 *
 * @author savin
 */
public class LazyContractDataModel extends AbstractLazyDataModel<Contract> {

    public LazyContractDataModel(List<Contract> datasource, Class<Contract> clazz) throws InstantiationException, IllegalAccessException {
        super(datasource, clazz);
    }

}
