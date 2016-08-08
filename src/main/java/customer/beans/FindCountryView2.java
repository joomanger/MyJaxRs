/*

 */
package customer.beans;

import customer.entities.CountryNew;
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
public class FindCountryView2 extends AbstractView<CountryNew>  {
    @Inject
    private CountryEJB2 client;

    public FindCountryView2() {
        super(CountryNew.class);
    }
    
    @PostConstruct
    @Override
    protected void init() {
        super.setEntities(client.findAll());
    }
    
}
