/*

 */
package rw.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWStationVL;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class FindRWStationView extends AbstractView<RWStationVL> {

    @Inject
    private RWStationCBean client;

    public FindRWStationView() {
        super(RWStationVL.class);
    }

    @PostConstruct
    @Override
    protected void init() {
        setEntities(client.findAllVL());
    }

}
