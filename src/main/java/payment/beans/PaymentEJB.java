package payment.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lookup.entities.Lookup;
import payment.entities.Payment;
import service.AbstractEJB;

/**
 *
 * @author savin
 */
@Stateless
public class PaymentEJB extends AbstractEJB<Payment> {

    @Inject
    private EntityManager em;

    public PaymentEJB() {
        super(Payment.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
