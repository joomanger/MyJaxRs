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
public class OpenRoadView extends AbstractView<RWRoad> {
    
    @Inject
    private RoadCBean client;
    @Inject
    private FindRoadSession fls;
 
    public OpenRoadView() {
        super(RWRoad.class);
    }
    
    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(client.find(fls.getRwr_code()));
    }
    
}
