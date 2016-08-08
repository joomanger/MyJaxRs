package service;

import java.util.List;

/**
 *
 * @author savin
 * @param <T>
 */
public interface IEJB<T> {

    public T find(Object p_id);

    public List<T> findAll();
    
    public String validateMyEntity(T entity);
    
    public String create(T entity);
    
    public String edit(T entity);
    
    public String remove(T entity);
    
    public void sendMessage(String status, String success_msg);
    
    
}
