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

    protected abstract AbstractView<T> getOpenView();

    protected abstract AbstractView<T> getFindView();

    protected abstract AbstractView<T> getNewView();

    public T find(Object p_id) {
        return getEJB().find(p_id);
    }

    public List<T> findAll() {
        return getEJB().findAll();
    }

    public void createEntity(String outcome) {
        if (getNewView().getEntity() instanceof WhoIS) {
//            WhoIS whoIS = (WhoIS) getNewView().getEntity();
//            whoIS.setCreatedBy(sa.getCurrentUser().getUser_id());
//            whoIS.setCreationDate(Calendar.getInstance().getTime());
//            whoIS.setLastUpdatedBy(sa.getCurrentUser().getUser_id());
//            whoIS.setLastUpdateDate(Calendar.getInstance().getTime());
            sa.createWHO((WhoIS) getNewView().getEntity());
        }
        getEJB().create(getNewView().getEntity());
        FacesContext.getCurrentInstance().getApplication().
                getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, outcome);
    }

    public void changeEntity() {
        if (getNewView().getEntity() instanceof WhoIS) {
//            WhoIS whoIS = (WhoIS) getNewView().getEntity();
//            whoIS.setLastUpdatedBy(sa.getCurrentUser().getUser_id());
//            whoIS.setLastUpdateDate(Calendar.getInstance().getTime());
            sa.createWHO((WhoIS) getNewView().getEntity());
        }
        getEJB().edit(getOpenView().getEntity());
    }

    public void deleteSelectedEntities() {
        getFindView().getSelectedEntities().stream().map((entity) -> {
            getEJB().remove(entity);
            return entity;
        }).forEach((entity) -> {
            getFindView().getEntities().remove(entity);
        });

    }
}
