package rw.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWStation;
import rw.entities.RWStationTL;
import service.AbstractView;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewRWStationView extends AbstractView<RWStation> {

    @Inject
    private SessionActions sa;
    private final RWStation station = new RWStation();
    private String language;
    private String name;

//    public NewRWStationView() {
//        super(RWStation.class);
//    }

    @PostConstruct
    @Override
    protected void init() {
        for (String l : sa.getSystemLanguages()) {
            RWStationTL s = new RWStationTL();
            s.setLanguage(l);
            station.addRWStationTL(s);
        }
        super.setEntity(station);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
