package beans.payment;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entities.payment.PaymentTerm;
import service.AbstractView;
import service.Secure;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class FindPaymentView extends AbstractView<PaymentTerm> {

    @Inject
    private PaymentCBean client;
    
//    public FindPaymentView() {
//        super(Payment.class);
//    }

    @Override
    @PostConstruct
    protected void init() {
        super.setEntities(client.findAll());
    }

}
