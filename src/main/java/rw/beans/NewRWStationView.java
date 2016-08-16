package rw.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWStation;
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
    private SessionActions sc;
    private final RWStation station = new RWStation();
    private String language;
    private String name;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
