package service;

import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractClientBean2<T> {

    @Inject
    private SessionActions sa;

    protected abstract AbstractEJB2<T> getEJB();

//    protected abstract AbstractView<T> getOpenView();
//
//    protected abstract AbstractView<T> getFindView();
//
//    protected abstract AbstractView<T> getNewView();

    public T find(Object p_id) {
        return getEJB().find(p_id);
    }

    public List<T> findAll() {
        return getEJB().findAll();
    }

    public void createEntity(IView view,String outcome) {
        if (view.getEntity() instanceof WhoIS) {
//            WhoIS whoIS = (WhoIS) getNewView().getEntity();
//            whoIS.setCreatedBy(sa.getCurrentUser().getUser_id());
//            whoIS.setCreationDate(Calendar.getInstance().getTime());
//            whoIS.setLastUpdatedBy(sa.getCurrentUser().getUser_id());
//            whoIS.setLastUpdateDate(Calendar.getInstance().getTime());
            sa.createWHO((WhoIS) view.getEntity());
        }
        getEJB().create((T)view.getEntity());
        FacesContext.getCurrentInstance().getApplication().
                getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, outcome);
    }

    public void changeEntity(IView view) {
        if (view.getEntity() instanceof WhoIS) {
//            WhoIS whoIS = (WhoIS) getNewView().getEntity();
//            whoIS.setLastUpdatedBy(sa.getCurrentUser().getUser_id());
//            whoIS.setLastUpdateDate(Calendar.getInstance().getTime());
            sa.createWHO((WhoIS) view.getEntity());
        }
        getEJB().edit((T)view.getEntity());
    }

    public void deleteSelectedEntities(IView view) {
        view.getSelectedEntities().stream().map((entity) -> {
            getEJB().remove((T)entity);
            return entity;
        }).forEach((entity) -> {
            view.getEntities().remove(entity);
        });

    }
}
