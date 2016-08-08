package customer.beans;

import customer.entities.Country;
import javax.annotation.PostConstruct;
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
public class OpenCountryView extends AbstractView<Country>{
     @Inject
    private CountryCBean client;
    @Inject
    private FindCountrySession fls;
    
    //Поля для создания новой строки
//    private String lookupValue;
//    private String lookupValueDescription;

    public OpenCountryView() {
        super(Country.class);
    }
    
    @PostConstruct
    @Override
    protected void init() {
        //super.setEntity(client.find(fls.getCountry_id()));
        
    }
}
