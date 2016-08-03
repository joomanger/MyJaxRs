package customer.beans;

import customer.entities.Country;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class CountryCBean extends AbstractClientBean<Country> {

    @Inject
    private CountryEJB ejb;
    @Inject
    private FindCountryView fcv;
    @Inject
    private NewCountryView ncv;
    @Inject
    private OpenCountryView ocv;

    public CountryCBean() {
        super(Country.class);
    }

    @Override
    protected AbstractEJB<Country> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<Country> getOpenView() {
        return ocv;
    }

    @Override
    protected AbstractView<Country> getFindView() {
        return fcv;
    }

    @Override
    protected AbstractView<Country> getNewView() {
        return ncv;
    }

}
