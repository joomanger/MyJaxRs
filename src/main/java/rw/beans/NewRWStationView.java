package rw.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import rw.entities.RWStation;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewRWStationView extends AbstractView<RWStation> {

    private final RWStation station = new RWStation();
    private String language;
    private String name;
    

    public NewRWStationView() {
        super(RWStation.class);
    }

    @PostConstruct
    @Override
    protected void init() {
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
