package beans.sys;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
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
public class NewViewView implements Serializable {

    private View view=new View();

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

}
