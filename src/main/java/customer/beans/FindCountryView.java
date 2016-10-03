/*

 */
package customer.beans;

import customer.entities.Country;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractView;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class FindCountryView extends AbstractView<Country> {

    @Inject
    private CountryCBean client;

//    public FindCountryView() {
//        super(CountryVL.class);
//    }

    @PostConstruct
    @Override
    protected void init() {
        setEntities(client.findAll());
    }

}
