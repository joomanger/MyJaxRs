package service;

import javax.ws.rs.core.Response;

/**
 *
 * @author savin
 * @param <T>
 */
public interface ClientOperations<T> {

     T getItem(Class<T> type, Object value);

     T[] getItems(Class<?> type);

     Response editItem(Object obj, String success_msg);

     Response addItem(Object obj, String success_msg);

     Response deleteItem(Object obj, String success_msg);

}
