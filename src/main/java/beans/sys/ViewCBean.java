package beans.sys;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import entities.sys.View;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class ViewCBean {

    @Inject
    private ViewEJB ejb;

    @Inject
    private FindViewView fvv;
    @Inject
    private OpenViewView ovv;
    @Inject
    private NewViewView nvv;

    public List<View> findAll() {
        return ejb.findAll();
    }

    public void deleteViews() {
        for (View v : fvv.getSelectedViews()) {
            String status = ejb.remove(v);
            ejb.sendMessage(status, "Представление " + v.getViewName() + " удалено успешно");
        }
    }

    public String saveViewOVV() {
        View view = ovv.getView();
        String status = ejb.edit(view);
        ejb.sendMessage(status, "Представление " + view.getViewName() + " изменено успешно");
        return "views";
    }

    public String createView() {
        View view = nvv.getView();
        String status = ejb.create(view);
        ejb.sendMessage(status, "Представление " + view.getViewName() + " создано успешно");
        return "views";
    }

    public View findViewById(Long p_view_id) {
        return ejb.find(p_view_id);
    }
    
    

}
