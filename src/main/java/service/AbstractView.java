package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractView<T> implements Serializable,IView<T> {

    private List<T> entities = new ArrayList<>();
    private List<T> selectedEntities = new ArrayList<>();
    private List<T> filteredEntities;
    private T entity;
    private List<Integer> linesForSave = new ArrayList<>();
    private List<Object> selectedEntityLines = new ArrayList<>();
    
    protected abstract void init();

    @Override
    public List<T> getEntities() {
        return entities;
    }

    @Override
    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    @Override
    public List<T> getSelectedEntities() {
        return selectedEntities;
    }

    @Override
    public void setSelectedEntities(List<T> selectedEntities) {
        this.selectedEntities = selectedEntities;
    }

    @Override
    public T getEntity() {
        return entity;
    }

    @Override
    public void setEntity(T entity) {
        this.entity = entity;
    }

    public List<Integer> getLinesForSave() {
        return linesForSave;
    }

    public void setLinesForSave(List<Integer> linesForSave) {
        this.linesForSave = linesForSave;
    }

    @Override
    public List<Object> getSelectedEntityLines() {
        return selectedEntityLines;
    }

    @Override
    public void setSelectedEntityLines(List<Object> selectedEntityLines) {
        this.selectedEntityLines = selectedEntityLines;
    }

    @Override
    public List<T> getFilteredEntities() {
        return filteredEntities;
    }

    @Override
    public void setFilteredEntities(List<T> filteredEntities) {
        this.filteredEntities = filteredEntities;
    }

}
