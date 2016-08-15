/*

 */
package customer.beans;

import customer.entities.CountryVL;
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
public class FindCountryView extends AbstractView<CountryVL> {

    @Inject
    private CountryCBean client;

    public FindCountryView() {
        super(CountryVL.class);
    }

    @PostConstruct
    @Override
    protected void init() {
        setEntities(client.findAllVL());
    }

}
