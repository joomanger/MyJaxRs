package payment.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import payment.entities.Payment;
import service.AbstractView;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class OpenPaymentView extends AbstractView<Payment> {
    
    @Inject
    private PaymentCBean client;
    @Inject
    private FindPaymentSession fls;
 
//    public OpenPaymentView() {
//        super(Payment.class);
//    }
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(client.find(fls.getPayment_id()));
    }
    
}
