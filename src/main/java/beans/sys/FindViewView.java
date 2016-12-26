package beans.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class FindViewView implements Serializable {

    private List<View> selectedViews = new ArrayList<>();

    public List<View> getSelectedViews() {
        return selectedViews;
    }

    public void setSelectedViews(List<View> selectedViews) {
        this.selectedViews = selectedViews;
    }

}
