package so.saleorder.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.AbstractEJB2;
import so.entities.Attachment;

/**
 *
 * @author savin
 */
@Stateless
public class AttachmentEJB extends AbstractEJB2<Attachment> {

    @PersistenceContext(unitName = "myjaxrs")
    private EntityManager em;

    public AttachmentEJB() {
        super(Attachment.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
