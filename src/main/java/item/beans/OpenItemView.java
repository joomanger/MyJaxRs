package item.beans;

import item.entities.Item;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractView;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class OpenItemView extends AbstractView<Item> {
    
    @Inject
    private ItemCBean client;
    @Inject
    private FindItemSession fis;
    
//    public OpenItemView() {
//        super(Item.class);
//    }
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @Override
    @PostConstruct
    protected void init() {
        super.setEntity(client.find(fis.getItem_id()));
    }
    
}
