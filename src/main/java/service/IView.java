package service;

import java.util.List;

/**
 *
 * @author savin
 */
public interface IView<T> {

    T getEntity();

    void setEntity(T entity);

    List<T> getEntities();

    void setEntities(List<T> entities);

    List<T> getSelectedEntities();

    void setSelectedEntities(List<T> selectedEntities);

    List<Object> getSelectedEntityLines();

    void setSelectedEntityLines(List<Object> selectedEntityLines);

    List<T> getFilteredEntities();

    void setFilteredEntities(List<T> filteredEntities);

}
