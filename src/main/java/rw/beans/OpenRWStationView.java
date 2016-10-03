package rw.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import rw.entities.RWStation;
import service.AbstractView;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class OpenRWStationView extends AbstractView<RWStation> {

    @Inject
    private RWStationCBean client;
    @Inject
    private FindRWStationSession fls;

    private String language;
    private String name;
   

//    public OpenRWStationView() {
//        super(RWStation.class);
//    }

    @PostConstruct
    @Override
    protected void init() {
        System.out.println("rws_code="+fls.getRws_code());
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


}
