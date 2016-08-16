package rw.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWRoad;
import service.AbstractView;
import service.SessionActions;

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
    @Inject
    private SessionActions sa;

    public OpenRoadView() {
        super(RWRoad.class);
    }

    @PostConstruct
    @Override
    protected void init() {
        RWRoad r = client.find(fls.getRwr_code());
        try {
            r.getCountry().getCountryTL().stream().filter((tl) -> (tl.getLanguage().equals(sa.getLanguage()))).forEach((tl) -> {
                r.getCountry().setName(tl.getName());
            });
        } catch (NullPointerException ex) {

        }
        super.setEntity(r);
    }

}
