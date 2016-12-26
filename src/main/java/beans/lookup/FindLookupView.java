package beans.lookup;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entities.lookup.Lookup;
import service.AbstractView;
import service.Secure;

/**
 *
 * @author savin
 */

@Named
@ViewScoped
@Secure
public class FindLookupView extends AbstractView<Lookup> {

    @Inject
    private LookupCBean client;

//    public FindLookupView() {
//        super(Lookup.class);
//    }

    @Override
    @PostConstruct
    protected void init() {
        super.setEntities(client.findAll());
    }

}
