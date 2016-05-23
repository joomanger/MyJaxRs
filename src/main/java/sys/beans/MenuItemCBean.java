package sys.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sys.entities.MenuItem;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class MenuItemCBean {

    @Inject
    private MenuItemEJB ejb;

    public MenuItem getItem(Long p_id) {
        return ejb.find(p_id);
    }

}
