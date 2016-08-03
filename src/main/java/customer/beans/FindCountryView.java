package customer.beans;

import customer.entities.Country;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class FindCountryView extends AbstractView<Country> {
    @Inject
    private CountryEJB client;

    public FindCountryView() {
        super(Country.class);
    }
    
    @PostConstruct
    @Override
    protected void init() {
        super.setEntities(client.findByLang());
    }

}
