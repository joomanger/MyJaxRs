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
public class OpenItemView extends AbstractView<Item> {
    
    @Inject
    private ItemCBean client;
    @Inject
    private FindItemSession fis;
    
    public OpenItemView() {
        super(Item.class);
    }
    
    @Override
    @PostConstruct
    protected void init() {
        super.setEntity(client.find(fis.getItem_id()));
    }
    
}