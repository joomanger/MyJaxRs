package service;

import java.util.List;

/**
 *
 * @author savin
 */
public interface IClientBean<T> {
    
    public T find(Long p_id);

    public List<T> findAll();
    
    public String createEntity(String backURL);

    public void changeEntity();
    
    public void deleteSelectedEntities();

}
