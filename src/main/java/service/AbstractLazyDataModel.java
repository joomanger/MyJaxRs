package service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractLazyDataModel<T> extends LazyDataModel<T> {

    private List<T> datasource;
    private String getIDMethodName;

    public AbstractLazyDataModel(List<T> datasource, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        this.datasource = datasource;
        for (Field f : clazz.getDeclaredFields()) {
            for (Annotation a : f.getDeclaredAnnotations()) {
                if (a.annotationType().getSimpleName().equals("Id")) {
                    this.getIDMethodName = "get" + Character.toUpperCase(f.getName().charAt(0)) + f.getName().substring(1);
                    break;
                }
            }
            if (!this.getIDMethodName.isEmpty()) {
                break;
            }
        }
        if (this.getIDMethodName.isEmpty()) {
            throw new IllegalAccessException("For " + clazz.getSimpleName() + " the getIDMethodName is not detected!");
        }
    }

    @Override
    public T getRowData(String rowKey) {
        for (T t : datasource) {
            try {
                String o = t.getClass().getDeclaredMethod(this.getIDMethodName, null).invoke(t, null).toString();
                if (o.equals(rowKey)) {
                    return t;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                System.out.println("getRowData: " + ex);
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(T c) {
        try {
            return c.getClass().getDeclaredMethod(this.getIDMethodName, null).invoke(c, null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            System.out.println("getRowKey: " + ex);
        }
        return null;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<T> data = new ArrayList<>();

        //filter
        for (T c : datasource) {
            boolean match = true;

            if (filters != null) {

                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        Field f = c.getClass().getDeclaredField(filterProperty);
                        f.setAccessible(true);
                        String fieldValue = String.valueOf(f.get(c));

                        if (filterValue == null || fieldValue.toLowerCase().contains(filterValue.toString().toLowerCase())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                        match = false;
                    }
                }
            }

            if (match) {
                data.add(c);
            }
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}
