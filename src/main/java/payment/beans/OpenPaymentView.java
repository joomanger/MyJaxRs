package payment.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.entities.Lookup;
import payment.entities.Payment;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class OpenPaymentView extends AbstractView<Payment> {
    
    @Inject
    private PaymentCBean client;
    @Inject
    private FindPaymentSession fls;
 
    public OpenPaymentView() {
        super(Payment.class);
    }
    
    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(client.find(fls.getPayment_id()));
    }
    
}
