package beans.rw;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entities.rw.RWRoad;
import service.AbstractView;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
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
