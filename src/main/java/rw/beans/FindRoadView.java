package rw.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWRoad;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class FindRoadView extends AbstractView<RWRoad> {

    @Inject
    private RoadCBean client;

//    public FindRoadView() {
//        super(RWRoad.class);
//    }

    @Override
    @PostConstruct
    protected void init() {
        super.setEntities(client.findAll());
    }

}
