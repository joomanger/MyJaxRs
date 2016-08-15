package rw.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import rw.entities.RWRoad;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewRoadView extends AbstractView<RWRoad> {

    private final RWRoad entity = new RWRoad();
   
    public NewRoadView() {
        super(RWRoad.class);
    }

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(entity);
    }

}
