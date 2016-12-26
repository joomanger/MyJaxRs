package beans.lookup;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entities.lookup.LookupItem;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class LookupItemCBean extends AbstractClientBean<LookupItem> {

    @Inject
    private LookupItemEJB ejb;

//    public LookupItemCBean() {
//        super(LookupItem.class);
//    }

    @Override
    protected AbstractEJB<LookupItem> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<LookupItem> getOpenView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected AbstractView<LookupItem> getFindView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected AbstractView<LookupItem> getNewView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
