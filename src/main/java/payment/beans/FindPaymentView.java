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
public class FindPaymentView extends AbstractView<Payment> {

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
