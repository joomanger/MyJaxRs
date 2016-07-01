package service;

import java.util.List;

/**
 *
 * @author savin
 */
public interface IClientBean<T> {

    public T find(Long p_id);

    public List<T> findAll();

    public void saveEntity();
    
    public void deleteSelectedEntities();

}
