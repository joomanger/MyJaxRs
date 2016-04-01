package service;

import javax.ws.rs.core.Response;

/**
 *
 * @author savin
 * @param <T>
 */
public interface ClientOperations<T> {

    public T getItem(Class<T> type, Object value);

    public T[] getItems(Class<?> type);

    public Response editItem(Object obj, String success_msg);

    public Response addItem(Object obj, String success_msg);

    public Response deleteItem(Object obj, String success_msg);

}
