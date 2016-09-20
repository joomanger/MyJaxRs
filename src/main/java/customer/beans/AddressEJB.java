package customer.beans;

import customer.entities.Address;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class AddressEJB extends AbstractEJB<Address> {

    @Inject
    private EntityManager em;

    public AddressEJB() {
        super(Address.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
