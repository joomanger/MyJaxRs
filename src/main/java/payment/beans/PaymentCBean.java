package payment.beans;

import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import payment.entities.Payment;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class PaymentCBean extends AbstractClientBean<Payment> {

    @Inject
    private PaymentEJB ejb;

    @Inject
    private FindPaymentView flv;

    @Inject
    private NewPaymentView nlv;

    @Inject
    private OpenPaymentView olv;

    public PaymentCBean() {
        super(Payment.class);
    }

    @Override
    protected AbstractEJB<Payment> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<Payment> getOpenView() {
        return olv;
    }

    @Override
    protected AbstractView<Payment> getFindView() {
        return flv;
    }

    @Override
    protected AbstractView<Payment> getNewView() {
        return nlv;
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> l = super.findAll();
        Collections.sort(l);
        return l;
    }

}
