package item.beans;

import item.entities.Item;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class ItemEJB extends AbstractEJB<Item> {

    @Inject
    private EntityManager em;

    public ItemEJB() {
        super(Item.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
