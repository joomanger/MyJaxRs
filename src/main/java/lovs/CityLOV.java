package lovs;

import customer.beans.OpenCustomerView;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.beans.LookupCBean;

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
    private OpenCustomerView ocv;

    public List<String> completeItem(String query) {
        List<String> allItems = client.getCitiesByRegion(ocv.getRegion());
        List<String> filteredItems = new ArrayList<>();

        allItems.stream().filter((item) -> item.toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
            filteredItems.add(item);
        });
        return filteredItems;
    }

}
