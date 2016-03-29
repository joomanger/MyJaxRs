package utils;

/**
 *
 * @author savin
 * @param <T>
 */
public interface ClientOperations<T> {

    public T getItem(Class<T> type, Object value);

    public T[] getItems(Class<?> type);

    public void editItem(Object obj);

    public void addItem(Object obj, String success_msg);

    public void deleteItem(Object obj);

}
