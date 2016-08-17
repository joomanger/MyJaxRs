package customer.beans;

import customer.entities.Country;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewCountryView extends AbstractView<Country> {

    private final Country country = new Country();
    private String language;
    private String countryValue;
    private String countryDescription;

    public NewCountryView() {
        super(Country.class);
    }

    @PostConstruct
    @Override
    protected void init() {
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
