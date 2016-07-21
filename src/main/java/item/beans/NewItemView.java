package item.beans;

import item.entities.Item;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class NewItemView extends AbstractView<Item> {

    public NewItemView() {
        super(Item.class);
    }

    @Override
    @PostConstruct
    protected void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
