package sys.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sys.entities.View;

/**
 *
 * @author savin
 */
@Named
@ViewScoped
public class FindViewView implements Serializable {

    private List<View> selectedViews = new ArrayList<>();

    public List<View> getSelectedViews() {
        return selectedViews;
    }

    public void setSelectedViews(List<View> selectedViews) {
        this.selectedViews = selectedViews;
    }

}
