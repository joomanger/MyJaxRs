/*

 */
package customer.beans;

import customer.entities.CountryNew;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenCountryView2 extends AbstractView<CountryNew> {

    @Inject
    private CountryCBean client;
    @Inject
    private FindCountrySession2 fls;

    public OpenCountryView2() {
        super(CountryNew.class);
    }

    @Override
    protected void init() {
        super.setEntity(client.find(fls.getCountry_id()));
    }

}
