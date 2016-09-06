package service;

import java.util.List;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractClientBean<T> implements IClientBean<T> {

    //private final Class<T> entity;

//    public AbstractClientBean(Class<T> entity) {
//        this.entity = entity;
//    }

    protected abstract AbstractEJB<T> getEJB();

    protected abstract AbstractView<T> getOpenView();

    protected abstract AbstractView<T> getFindView();

    protected abstract AbstractView<T> getNewView();

    @Override
    public T find(Object p_id) {
        return getEJB().find(p_id);
    }

    @Override
    public List<T> findAll() {
        return getEJB().findAll();
    }

    @Override
    public String createEntity(String backURL) {

        String result = getEJB().validateMyEntity(getNewView().getEntity());
        if (result.equals(getEJB().SUCCESSFUL)) {
            String status = getEJB().create(getNewView().getEntity());
            getEJB().sendMessage(status, "Объект создан успешно");
            if (status.equals(getEJB().SUCCESSFUL)) {
                return backURL;
            } else {
                getEJB().sendMessage(status, null);
                return null;
            }
        } else {
            getEJB().sendMessage(result, null);
            return null;
        }
    }

    @Override
    public void changeEntity() {
        String result = getEJB().validateMyEntity(getOpenView().getEntity());
        if (result.equals(getEJB().SUCCESSFUL)) {
//            System.out.println("validation is OK");
            String status = getEJB().edit(getOpenView().getEntity());
//            System.out.println("status after create =>"+status);
            getEJB().sendMessage(status, "Объект обновлен успешно");
            if (!status.equals(getEJB().SUCCESSFUL)) {
//                System.out.println("status unsuccessful =>"+status);
                getEJB().sendMessage(status, null);
            }
        } else {
//            System.out.println("validation is not OK");
            getEJB().sendMessage(result, null);
        }
    }

    @Override
    public void deleteSelectedEntities() {
        for (T entity : getFindView().getSelectedEntities()) {
            String status = getEJB().remove(entity);
            if (status.equals(getEJB().SUCCESSFUL)) {
                getEJB().sendMessage(status, "Объект удален успешно");
                getFindView().getEntities().remove(entity);
            } else {
                getEJB().sendMessage(status, null);
            }
        }
    }
    
    
}
