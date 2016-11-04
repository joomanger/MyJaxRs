package service;

import java.util.List;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class AbstractClientBean<T> {
//
//    @Inject
//    private LookupCBean client;
//    @Inject
//    private SessionActions sa;

    protected abstract AbstractEJB<T> getEJB();

    protected abstract AbstractView<T> getOpenView();

    protected abstract AbstractView<T> getFindView();

    protected abstract AbstractView<T> getNewView();

    public T find(Object p_id) {
        return getEJB().find(p_id);
    }

    public List<T> findAll() {
        return getEJB().findAll();
    }

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

    public void changeEntity() {
        String result = getEJB().validateMyEntity(getOpenView().getEntity());
        if (result.equals(getEJB().SUCCESSFUL)) {
            String status = getEJB().edit(getOpenView().getEntity());
            getEJB().sendMessage(status, "Объект обновлен успешно");
            if (!status.equals(getEJB().SUCCESSFUL)) {
                getEJB().sendMessage(status, null);
            }
        } else {
            getEJB().sendMessage(result, null);
        }
    }

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

//    public LookupItemTL getLookupItemTL(String lookupName, String value) {
//        return client.findByName(lookupName).getTranslateObject(value, sa.getLanguage());
//    }
}
