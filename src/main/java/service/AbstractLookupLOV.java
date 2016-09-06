package service;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import lookup.beans.LookupCBean;
import lookup.entities.Lookup;
import lookup.entities.LookupItem;

/**
 *
 * @author savin
 */
public abstract class AbstractLookupLOV {

    @Inject
    private LookupCBean client;

    protected abstract String getLookupName();

    public List<String> completeItem(String query) {
        Lookup l = client.findByName(getLookupName());
        List<LookupItem> allItems = l.getLookupItems();
        List<String> filteredItems = new ArrayList<>();

        allItems.stream().filter((item) -> item.getValuez().toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
            if (item.getActiveStatus()) {
                filteredItems.add(item.getValuez());
            }
        });
        return filteredItems;
    }
}
