package lookup.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.entities.Lookup;
import service.AbstractFindView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class FindLookupView extends AbstractFindView<Lookup> {

    @Inject
    private LookupCBean client;

    public FindLookupView() {
        super(Lookup.class);
    }

    @Override
    @PostConstruct
    public void setClient() {
        super.setClient(client);
    }

}
