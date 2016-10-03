package payment.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
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
public class NewPaymentView extends AbstractView<Payment> {

    private final Payment entity = new Payment();
   
//    public NewPaymentView() {
//        super(Payment.class);
//    }
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(entity);
    }

}
