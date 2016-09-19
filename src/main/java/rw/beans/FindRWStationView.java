package rw.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWStationVL;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
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
