package customer.beans;

import customer.entities.Country;
import customer.entities.CountryTL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractView;
import service.Secure;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class NewCountryView extends AbstractView<Country> {
    @Inject
    private SessionActions sa;
    private final Country country = new Country();
    private String language;
    private String countryValue;
    private String countryDescription;

//    public NewCountryView() {
//        super(Country.class);
//    }

    @PostConstruct
    @Override
    protected void init() {
        for (String l : sa.getSystemLanguages()) {
            CountryTL s = new CountryTL();
            s.setLanguage(l);
            country.addCountryTL(s);
        }
        super.setEntity(country);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountryValue() {
        return countryValue;
    }

    public void setCountryValue(String countryValue) {
        this.countryValue = countryValue;
    }

    public String getCountryDescription() {
        return countryDescription;
    }

    public void setCountryDescription(String countryDescription) {
        this.countryDescription = countryDescription;
    }

}
