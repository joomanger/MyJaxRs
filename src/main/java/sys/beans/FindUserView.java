package sys.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractView;
import service.Secure;
import sys.entities.SysUser;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class FindUserView extends AbstractView<SysUser> {

    @Inject
    private UserCBean client;

    @Override
    @PostConstruct
    protected void init() {
        super.setEntities(client.findAll());
    }
}
