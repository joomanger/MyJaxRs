package lookup.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lookup.entities.Lookup;
import service.AbstractView;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class FindLookupView extends AbstractView<Lookup> {

    @Inject
    private LookupCBean client;

    public FindLookupView() {
        super(Lookup.class);
    }

    @Override
    @PostConstruct
    protected void init() {
        super.setEntities(client.findAll());
    }

}
