package beans.customer;

import entities.customer.Relationship;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class RelationshipEJB extends AbstractEJB<Relationship> {

    @Inject
    private EntityManager em;

    public RelationshipEJB() {
        super(Relationship.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
