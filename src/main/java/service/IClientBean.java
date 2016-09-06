package service;

import java.util.List;

/**
 *
 * @author savin
 * @param <T>
 */
public interface IClientBean<T> {
    
    T find(Object p_id);

    List<T> findAll();
    
    String createEntity(String backURL);

    void changeEntity();
    
    void deleteSelectedEntities();

}
