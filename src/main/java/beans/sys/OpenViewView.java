package beans.sys;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.Secure;
import entities.sys.View;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
@Secure
public class OpenViewView implements Serializable {

    @Inject
    private ViewEJB ejb;
    @Inject
    private FindViewSession fvs;

    private View view;
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    @PostConstruct
    private void init() {
        view = ejb.find(fvs.getView_id());
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

}
