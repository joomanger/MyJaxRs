package customer.beans;

import customer.entities.CountryNew;
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
public class CountryCBean extends AbstractClientBean<CountryNew> {

    @Inject
    private CountryEJB2 ejb;
    @Inject
    private FindCountryView2 fcv;
    @Inject
    private NewCountryView ncv;
    @Inject
    private OpenCountryView2 ocv;

    public CountryCBean() {
        super(CountryNew.class);
    }

    @Override
    protected AbstractEJB<CountryNew> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<CountryNew> getOpenView() {
        return ocv;
    }

    @Override
    protected AbstractView<CountryNew> getFindView() {
        return fcv;
    }

    @Override
    protected AbstractView<CountryNew> getNewView() {
        return null;
    }

}
