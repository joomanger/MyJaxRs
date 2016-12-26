package beans.item;

import entities.item.Item;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
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
public class NewItemView extends AbstractView<Item> {
    
//    public NewItemView() {
//        super(Item.class);
//    }

    @Override
    @PostConstruct
    protected void init() {
        super.setEntity(new Item());
    }

}
