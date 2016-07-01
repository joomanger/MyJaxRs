package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractFindView<T> implements IView, Serializable {

    private final Class<T> entityClass;

    private List<T> entities = new ArrayList<>();
    private List<T> selectedEntities = new ArrayList<>();

    private IClientBean<T> client;

    public abstract void setClient(); 

    public AbstractFindView(Class<T> entityClass) {
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

    public IClientBean<T> getClient() {
        return client;
    }

    public void setClient(IClientBean<T> client) {
        this.client = client;
        entities=client.findAll();
    }
    
    

}
