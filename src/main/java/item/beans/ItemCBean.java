package item.beans;

import item.entities.Item;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ItemCBean extends AbstractClientBean<Item> {

    @Inject
    private FindItemView fiv;

    @Inject
    private OpenItemView oiv;

    @Inject
    private NewItemView niv;

    @Inject
    private ItemEJB ejb;

//    public ItemCBean() {
//        super(Item.class);
//    }

    @Override
    protected AbstractEJB<Item> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<Item> getOpenView() {
        return oiv;
    }

    @Override
    protected AbstractView<Item> getFindView() {
        return fiv;
    }

    @Override
    protected AbstractView<Item> getNewView() {
        return niv;
    }

    @Override
    public List<Item> findAll() {
        List<Item> l = super.findAll();
        Collections.sort(l, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getName().
                        compareTo(o2.getName());
            }
        });
        //Collections.sort(items);
        return l;
    }

}
