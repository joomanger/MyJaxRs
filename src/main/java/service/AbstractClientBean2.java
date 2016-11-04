package service;

import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractClientBean2<T> {

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
        getEJB().create(getNewView().getEntity());
        FacesContext.getCurrentInstance().getApplication().
                getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, outcome);
    }

    public void changeEntity() {
        getEJB().edit(getOpenView().getEntity());
    }

    public void deleteSelectedEntities() {
        for (T entity : getFindView().getSelectedEntities()) {
            getEJB().remove(entity);
        }
    }
}
