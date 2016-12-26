package beans.payment;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import beans.lookup.LookupCBean;
import entities.lookup.Lookup;
import entities.lookup.LookupItemTL;
import entities.payment.PaymentTerm;
import service.AbstractView;
import service.Secure;
import service.SessionActions;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class OpenPaymentView extends AbstractView<PaymentTerm> {

    @Inject
    private PaymentCBean client;
    @Inject
    private LookupCBean lookupClient;
    @Inject
    private SessionActions sa;
    @Inject
    private FindPaymentSession fls;
    //Справочники
    private Lookup dayType;

//    public OpenPaymentView() {
//        super(Payment.class);
//    }
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(client.find(fls.getPayment_id()));
        dayType = lookupClient.findByName("DAYTYPES");
    }

    public LookupItemTL getDayTypeTL(String value) {
        try {
            return dayType.getTranslateObject(value, sa.getLanguage());
        } catch (NullPointerException ex) {
            return null;
        }
    }

}
