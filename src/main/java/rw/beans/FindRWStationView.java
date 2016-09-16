package rw.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import rw.entities.RWStation;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class FindRWStationView extends AbstractView<RWStation> {

    @Inject
    private RWStationCBean client;
    private LazyDataModel<RWStation> lazyModel;

    @PostConstruct
    @Override

    protected void init() {
//        List<RWStation> rw = client.findAll();
        try {
            lazyModel = new LazyRWSDataModel(client.findAll(), RWStation.class);
        } catch (IllegalAccessException | InstantiationException ex) {
            System.out.println(ex);
        }
        //setEntities(rw);
    }

    public LazyDataModel<RWStation> getLazyModel() {
        return lazyModel;
    }

}
