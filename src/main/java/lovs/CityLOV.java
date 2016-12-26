package lovs;

import beans.customer.FindCustomerSession;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import beans.lookup.LookupCBean;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class CityLOV {

    @Inject
    private LookupCBean client;

    @Inject
    private FindCustomerSession fcs;

    public List<String> completeItem(String query) {
        List<String> allItems = client.getCitiesByRegion(fcs.getRegion());
        List<String> filteredItems = new ArrayList<>();

        allItems.stream().filter((item) -> item.toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
            filteredItems.add(item);
        });
        return filteredItems;
    }

}
