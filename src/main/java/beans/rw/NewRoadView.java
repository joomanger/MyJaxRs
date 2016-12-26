package beans.rw;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
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
public class NewRoadView extends AbstractView<RWRoad> {

    private final RWRoad entity = new RWRoad();
   
//    public NewRoadView() {
//        super(RWRoad.class);
//    }

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(entity);
    }

}
