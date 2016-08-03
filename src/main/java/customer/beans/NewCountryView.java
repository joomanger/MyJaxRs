package customer.beans;

import customer.entities.Country;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractView;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewCountryView extends AbstractView<Country> {

    @Inject
    private SessionActions sc;
    private final Country country = new Country();

    public NewCountryView() {
        super(Country.class);
    }

    @PostConstruct
    @Override
    protected void init() {
        country.setLanguage(sc.getLanguage());
        super.setEntity(country);
    }

}
