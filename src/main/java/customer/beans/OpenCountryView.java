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
public class OpenCountryView extends AbstractView<Country> {

    @Inject
    private CountryCBean client;
    @Inject
    private FindCountrySession fls;

    private String language;
    private String countryValue;
    private String countryDescription;

//    public OpenCountryView() {
//        super(Country.class);
//    }

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(client.find(fls.getCountry_id()));
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
