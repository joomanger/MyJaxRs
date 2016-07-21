package item.beans;

import item.entities.Item;
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
public class FindItemView extends AbstractView<Item> {

    @Inject
    private ItemCBean client;

    public FindItemView() {
        super(Item.class);
    }

    @Override
    @PostConstruct
    protected void init() {
        super.setEntities(client.findAll());
    }

}
