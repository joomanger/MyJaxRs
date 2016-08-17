package rw.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWStation;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenRWStationView extends AbstractView<RWStation> {

    @Inject
    private RWStationCBean client;
    @Inject
    private FindRWStationSession fls;

    private String language;
    private String name;
    private String description;

    public OpenRWStationView() {
        super(RWStation.class);
    }

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(client.find(fls.getRws_code()));
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