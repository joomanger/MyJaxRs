package payment.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.beans.LookupCBean;
import lookup.entities.Lookup;
import lookup.entities.LookupItemTL;
import payment.entities.PaymentTerm;
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
public class NewPaymentView extends AbstractView<PaymentTerm> {

    //Справочники
    private Lookup dayType;
    @Inject
    private LookupCBean lookupClient;
    @Inject
    private SessionActions sa;

    private final PaymentTerm entity = new PaymentTerm();

    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    @Override
    protected void init() {
        super.setEntity(entity);
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
