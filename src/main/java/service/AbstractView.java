package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractView<T> implements Serializable {

    private final Class<T> entityClass;

    private List<T> entities = new ArrayList<>();
    private List<T> selectedEntities = new ArrayList<>();
    private List<T> filteredEntities;
    private T entity;
    private List<Integer> linesForSave = new ArrayList<>();
    private List<Object> selectedEntityLines = new ArrayList<>();

    protected abstract void init();

    public AbstractView(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    public List<T> getSelectedEntities() {
        return selectedEntities;
    }

    public void setSelectedEntities(List<T> selectedEntities) {
        this.selectedEntities = selectedEntities;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public List<Integer> getLinesForSave() {
        return linesForSave;
    }

    public void setLinesForSave(List<Integer> linesForSave) {
        this.linesForSave = linesForSave;
    }

    public List<Object> getSelectedEntityLines() {
        return selectedEntityLines;
    }

    public void setSelectedEntityLines(List<Object> selectedEntityLines) {
        this.selectedEntityLines = selectedEntityLines;
    }

    public List<T> getFilteredEntities() {
        return filteredEntities;
    }

    public void setFilteredEntities(List<T> filteredEntities) {
        this.filteredEntities = filteredEntities;
    }
    
}
