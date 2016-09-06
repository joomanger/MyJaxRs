package service;

import java.util.List;

/**
 *
 * @author savin
 * @param <T>
 */
public interface IEJB<T> {

     T find(Object p_id);

     List<T> findAll();
    
     String validateMyEntity(T entity);
    
     String create(T entity);
    
     String edit(T entity);
    
     String remove(T entity);
    
     void sendMessage(String status, String success_msg);
    
    
}
