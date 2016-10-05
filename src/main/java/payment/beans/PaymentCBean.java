package payment.beans;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import payment.entities.PaymentTerm;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class PaymentCBean extends AbstractClientBean<PaymentTerm> {

    @Inject
    private PaymentEJB ejb;

    @Inject
    private FindPaymentView flv;

    @Inject
    private NewPaymentView nlv;

    @Inject
    private OpenPaymentView olv;

//    public PaymentCBean() {
//        super(Payment.class);
//    }

    @Override
    protected AbstractEJB<PaymentTerm> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<PaymentTerm> getOpenView() {
        return olv;
    }

    @Override
    protected AbstractView<PaymentTerm> getFindView() {
        return flv;
    }

    @Override
    protected AbstractView<PaymentTerm> getNewView() {
        return nlv;
    }

    @Override
    public List<PaymentTerm> findAll() {
        List<PaymentTerm> l=ejb.findAll();
        Collections.sort(l, new Comparator<PaymentTerm>(){
            @Override
            public int compare(PaymentTerm o1, PaymentTerm o2) {
                return  o1.getCondition().compareTo(o2.getCondition());
            }
            
        });
        return l;
    }

}
