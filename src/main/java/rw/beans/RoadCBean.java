package rw.beans;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWRoad;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class RoadCBean extends AbstractClientBean<RWRoad> {

    @Inject
    private RoadEJB ejb;

    @Inject
    private FindRoadView flv;

    @Inject
    private NewRoadView nlv;

    @Inject
    private OpenRoadView olv;

//    public RoadCBean() {
//        super(RWRoad.class);
//    }

    @Override
    protected AbstractEJB<RWRoad> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<RWRoad> getOpenView() {
        return olv;
    }

    @Override
    protected AbstractView<RWRoad> getFindView() {
        return flv;
    }

    @Override
    protected AbstractView<RWRoad> getNewView() {
        return nlv;
    }

    @Override
    public List<RWRoad> findAll() {
        List<RWRoad> l = super.findAll();
//        Collections.sort(l);
        return l;
    }

}
