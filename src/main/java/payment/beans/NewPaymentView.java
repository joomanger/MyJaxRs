package payment.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
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
public class NewPaymentView extends AbstractView<Payment> {

    private final Payment entity = new Payment();
   
    public NewPaymentView() {
        super(Payment.class);
    }

    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(entity);
    }

}
