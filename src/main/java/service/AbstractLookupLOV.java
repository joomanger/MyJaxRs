package service;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import beans.lookup.LookupCBean;
import entities.lookup.Lookup;
import entities.lookup.LookupItem;

/**
 *
 * @author savin
 */
public abstract class AbstractLookupLOV {

    @Inject
    private LookupCBean client;
//    @Inject
//    private SessionActions sa;

    private Lookup lookup;

    protected abstract String getLookupName();

//    public LookupItemTL getTranslateObject(String p_value) {
//        try {
//            return lookup.getTranslateObject(p_value, sa.getLanguage());
//        } catch (NullPointerException ex) {
//            return null;
//        }
//    }

    public List<String> completeItem(String query) {
        lookup = client.findByName(getLookupName());
        List<LookupItem> allItems = lookup.getLookupItems();
        List<String> filteredItems = new ArrayList<>();

        allItems.stream().filter((item) -> item.getValuez().toLowerCase().contains(query.toLowerCase())).forEach((item) -> {
            if (item.getActiveStatus()) {
                filteredItems.add(item.getValuez());
            }
        });
        return filteredItems;
    }
}
