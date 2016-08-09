/*

 */
package customer.beans;

import customer.entities.CountryTL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class FindCountryView2 /*extends AbstractView<CountryNew>*/ {

    @Inject
    private CountryEJB2 client;

    private List<CountryTL> entities = new ArrayList<>();
    private List<CountryTL> selectedEntities = new ArrayList<>();

//    public FindCountryView2() {
//        super(CountryNew.class);
//    }
    @PostConstruct
    // @Override
    protected void init() {
        setEntities(client.findByLang());
        //client.testMapping();
    }

    public CountryEJB2 getClient() {
        return client;
    }

    public void setClient(CountryEJB2 client) {
        this.client = client;
    }

    public List<CountryTL> getEntities() {
        return entities;
    }

    public void setEntities(List<CountryTL> entities) {
        this.entities = entities;
    }

    public List<CountryTL> getSelectedEntities() {
        return selectedEntities;
    }

    public void setSelectedEntities(List<CountryTL> selectedEntities) {
        this.selectedEntities = selectedEntities;
    }

}
