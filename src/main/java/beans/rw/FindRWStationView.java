package beans.rw;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entities.rw.RWStationVL;
import service.AbstractView;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class FindRWStationView extends AbstractView<RWStationVL> {

    @Inject
    private RWStationCBean client;
    private LazyRWSDataModel lazyModel;

    @PostConstruct
    @Override
    protected void init() {
        try {
            lazyModel = new LazyRWSDataModel(client.findAllVL(), RWStationVL.class);
        } catch (IllegalAccessException | InstantiationException ex) {
            System.out.println(ex);
        }
    }

    public LazyRWSDataModel getLazyModel() {
        return lazyModel;
    }

}
