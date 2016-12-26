package beans.payment;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import entities.payment.PaymentTerm;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class PaymentEJB extends AbstractEJB<PaymentTerm> {

    @Inject
    private EntityManager em;

    public PaymentEJB() {
        super(PaymentTerm.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
